package io.wcygan
package examples

object HelloWorld {
  @main def hello(): Unit = {
    println("Hello World")
  }

  @main def helloUpperCase(args: String*): Unit ={
    args.foreach(x => println(x.toUpperCase()))
  }

  @main def helloArgs(args: String*): Unit ={
    args foreach println
  }
}
