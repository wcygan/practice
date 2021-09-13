package numbers

type Counter struct {
	count int
}

func (c *Counter) Increment() int {
	c.count += 1
	return c.count
}

func (c *Counter) Count() int {
	return c.count
}
