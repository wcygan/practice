package queue

type Queue interface {
	Push(x interface{})
	Pop() interface{}
}
