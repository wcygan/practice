package io.wcygan
package examples

object Indentation {
  @main def optional(): Unit = {
    for i <- 1 to 10 do
      if i % 2 == 0 then
        println(i)
  }

  @main def braces(): Unit = {
    for i <- 1 to 10 do {
      if (i % 2 == 0) {
        println(i)
      }
    }
  }
}
