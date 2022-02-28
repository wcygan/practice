package io.wcygan
package examples

object Indentation {
  /**
   * To disable "significant" indentation, use the compiler flag: `-no-indent`
   */
  @main def optional(): Unit = {
    for i <- 1 to 10 do
      if i % 2 == 0 then
        println(i)
    end for
  }

  @main def braces(): Unit = {
    for i <- 1 to 10 do {
      if (i % 2 == 0) {
        println(i)
      }
    }
  }
}
