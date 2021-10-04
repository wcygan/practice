package pipelines

import "sync"

// Values takes integers and sends them to an output stream
func Values(done <-chan interface{}, integers ...int) <-chan int {
	intStream := make(chan int)
	go func() {
		defer close(intStream)
		for _, i := range integers {
			select {
			case <-done:
				return
			case intStream <- i:
			}
		}
	}()
	return intStream
}

// Multiply takes a stream of integers and multiplies them, sending them to an output stream
func Multiply(done <-chan interface{}, intStream <-chan int, multiplier int) <-chan int {
	multipliedStream := make(chan int)
	go func() {
		defer close(multipliedStream)
		for i := range intStream {
			select {
			case <-done:
				return
			case multipliedStream <- i * multiplier:
			}
		}
	}()
	return multipliedStream
}

// Add takes a stream of integers and adds an integer to them, sending them to an output stream
func Add(done <-chan interface{}, intStream <-chan int, additive int) <-chan int {
	addedStream := make(chan int)
	go func() {
		defer close(addedStream)
		for i := range intStream {
			select {
			case <-done:
				return
			case addedStream <- i + additive:
			}
		}
	}()
	return addedStream
}

// Repeat continuously repeats the values provided to it
func Repeat(done <-chan interface{}, values ...interface{}) <-chan interface{} {
	valueStream := make(chan interface{})
	go func() {
		defer close(valueStream)
		// repeat infinitely
		for {
			// send values
			for _, v := range values {
				select {
				case <-done:
					return
				case valueStream <- v:
				}
			}
		}
	}()
	return valueStream
}

// RepeatFn continuously repeats the given function and sends its results over a stream
func RepeatFn(done <-chan interface{}, fn func() interface{}) <-chan interface{} {
	valueStream := make(chan interface{})
	go func() {
		defer close(valueStream)
		for {
			select {
			case <-done:
				return
			case valueStream <- fn():
			}
		}
	}()
	return valueStream
}

// Take retrieves n items from the given value stream
func Take(done <-chan interface{}, valueStream <-chan interface{}, n int) <-chan interface{} {
	takeStream := make(chan interface{})
	go func() {
		defer close(takeStream)
		for i := 0; i < n; i++ {
			select {
			case <-done:
				return
			case takeStream <- <-valueStream:
			}
		}
	}()
	return takeStream
}

// CombineIntStreams takes multiple integer streams and combines them into a single integer stream
func CombineIntStreams(done <-chan interface{}, intStreams ...<-chan int) <-chan int {
	var wg sync.WaitGroup
	multiplexedStream := make(chan int)

	multiplex := func(c <-chan int) {
		defer wg.Done()
		for i := range c {
			select {
			case <-done:
				return
			case multiplexedStream <- i:
			}
		}
	}

	wg.Add(len(intStreams))
	for _, intStream := range intStreams {
		go multiplex(intStream)
	}

	go func() {
		wg.Wait()
		close(multiplexedStream)
	}()

	return multiplexedStream
}

// OrDone takes values from a given stream until a message from "done" is received
func OrDone(done, c <-chan interface{}) <-chan interface{} {
	valueStream := make(chan interface{})
	go func() {
		defer close(valueStream)
		for {
			select {
			case <-done:
				return
			case v, ok := <-c:
				if !ok {
					return
				}
				select {
				case valueStream <- v:
				case <-done:
				}
			}
		}
	}()
	return valueStream
}

// Tee splits a stream of incoming values into two channels
// WARNING: values must be pulled from BOTH outgoing channels in stride, else the program blocks forever
// This means that every call to <-out1 must have another call to <-out2 in order to become unblocked.
func Tee(done, incoming <-chan interface{}) (_, _ <-chan interface{}) {
	out1 := make(chan interface{})
	out2 := make(chan interface{})

	go func() {
		defer close(out1)
		defer close(out2)
		for val := range OrDone(done, incoming) {
			// set out1 and out2 to the original channels
			var out1, out2 = out1, out2
			// send the value to each channel
			for i := 0; i < 2; i++ {
				select {
				case <-done:
				case out1 <- val:
					// out1 should yield to out2, so we set out1 to nil
					out1 = nil
				case out2 <- val:
					// out2 should yield to ou1, so we set out2 to nil
					out2 = nil
				}
			}
		}
	}()

	return out1, out2
}

// Bridge flattens a stream of channels into one channel streaming all elements received from the channels
func Bridge(done <-chan interface{}, channelStream <-chan <-chan interface{}) <-chan interface{} {
	valueStream := make(chan interface{})
	go func() {
		defer close(valueStream)
		for {
			var stream <-chan interface{}

			// receive a new stream
			select {
			case maybeStream, ok := <-channelStream:
				if !ok {
					return
				}
				stream = maybeStream
			case <-done:
				return
			}

			// "flatten" or redirect all values from a stream
			for val := range OrDone(done, stream) {
				select {
				case valueStream <- val:
				case <-done:
				}
			}
		}
	}()
	return valueStream
}
