package main

import (
	"fmt"
	"os"
	"path/filepath"
	"sync"
	"time"
)

// source: https://www.gopl.io/ chapter 8

var done = make(chan struct{})

func canceled () bool {
	select {
	case <- done:
		return true
	default:
		return false
	}
}

func main() {
	roots := os.Args[1:]
	if len(roots) == 0 {
		roots = []string{"."}
	}

	// Cancel when input is detected
	go func() {
		// Block while waiting for input
		os.Stdin.Read(make([]byte, 1))
		close(done)
	}()

	fileSizes := make(chan int64)
	var wg sync.WaitGroup

	for _, root := range roots {
		wg.Add(1)
		go walkDir(root, &wg, fileSizes)
	}

	go func() {
		wg.Wait()
		close(fileSizes)
	}()

	tick := time.Tick(500 * time.Millisecond)
	var nfiles, nbytes int64

loop:
	for {
		select {
		case <-done:
			for range fileSizes {
				// drain fileSizes to prevent goroutine leak
			}
			return
		case size, ok := <-fileSizes:
			if !ok {
				break loop
			}
			nfiles++
			nbytes += size
		case <-tick:
			printDiskUsage(nfiles, nbytes)
		}
	}

	printDiskUsage(nfiles, nbytes)
}

func printDiskUsage(nfiles, nbytes int64) {
	fmt.Printf("%d files  %.1f GB\n", nfiles, float64(nbytes)/1e9)
}

func walkDir(dir string, wg *sync.WaitGroup, fileSizes chan<- int64) {
	defer wg.Done()
	if canceled() {
		return
	}

	for _, entry := range directoryEntries(dir) {
		if entry.IsDir() {
			wg.Add(1)
			subdir := filepath.Join(dir, entry.Name())
			go walkDir(subdir, wg, fileSizes)
		} else {
			fileSizes <- entry.Size()
		}
	}
}

var semaphore = make(chan struct{}, 30)

func directoryEntries(dir string) []os.FileInfo {
	select {
	case semaphore <- struct{}{}:
	case <-done:
		return nil
	}
	defer func() {<-semaphore}()

	f, err := os.Open(dir)
	if err != nil {
		fmt.Fprintf(os.Stderr, "du: %v\n", err)
		return nil
	}
	defer f.Close()

	entries, err := f.Readdir(0) // 0 => no limit; read all entries
	if err != nil {
		fmt.Fprintf(os.Stderr, "du: %v\n", err)
		// Don't return: Readdir may return partial results.
	}
	return entries
}