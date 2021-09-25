package queue


// BoundedChannelQueue WARNING: has the potential to block if a channel send "ch <- x" is attempted on a full queue
type BoundedChannelQueue struct {
	queue chan interface{}
}

func NewBoundedChannelQueue() *BoundedChannelQueue {
	return &BoundedChannelQueue{queue: make(chan interface{}, 1000)}
}

func (c *BoundedChannelQueue) Push(x interface{}) {
	c.queue <- x
}

func (c *BoundedChannelQueue) Pop() interface{} {
	return <- c.queue
}