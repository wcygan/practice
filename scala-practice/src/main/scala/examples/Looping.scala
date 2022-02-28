package io.wcygan
package examples

class Looping {

  def forLoopWithRange(): Unit = {
    for i <- 1 to 10 do
      println(i)
  }

  def forLoop(strings: String*): Unit = {
    for arg <- strings do
      println(arg)
  }

  def forEach(strings: String*): Unit = {
    strings foreach println
  }

  def whileLoop(strings: String*): Unit = {
    var i = 0

    while i < strings.length do
      if i != 0 then
        print("Hello!")
      i += 1
  }
}
