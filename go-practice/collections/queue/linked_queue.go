package queue

import "sync"

type node struct {
	data interface{}
	next *node
}

type LinkedQueue struct {
	head *node
	tail *node
	lock *sync.Mutex
}

func NewLinkedQueue() *LinkedQueue {
	return &LinkedQueue{
		lock: &sync.Mutex{},
	}
}

func (l *LinkedQueue) Push(x interface{}) {
	l.lock.Lock()
	defer l.lock.Unlock()

	n := &node{data: x}

	if l.tail == nil {
		// add node to empty queue
		l.tail, l.head = n, n
	} else {
		// add node to non-empty queue
		l.tail.next, l.tail = n, n
	}
}

func (l *LinkedQueue) Pop() interface{} {
	l.lock.Lock()
	defer l.lock.Unlock()

	if l.head == nil {
		// nothing on queue
		return nil
	}

	// remove node from queue
	n := l.head
	l.head = n.next

	if l.head == nil {
		// queue has no elements now
		l.tail = nil
	}

	return n.data
}
