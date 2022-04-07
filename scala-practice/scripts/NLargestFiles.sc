import mainargs.main

// example usage:
//    $ amm NLargestFiles.sc 5
@main def main(args: String*): Unit = {
  if (args.isEmpty) {
    exit()
  }

  args(0).toIntOption match {
    case None => exit()
    case Some(value) =>
      if (value <= 0) {
        exit()
      }
      printNLargestFiles(value)
  }
}

def exit(): Unit = {
  System.err.println("please provide a single positive integer!")
  System.exit(-1)
}

def printNLargestFiles(n: Int): Unit = {
  val x = os.walk(os.pwd)
    .filter(os.isFile)
    .map(p => (os.size(p) / 1000, p.relativeTo(os.pwd)))
    .sortBy(-_._1)
    .take(n)
    .zipWithIndex

  println(s"Here are the largest ${n} entries:")
  x.foreach(v => {
    println(s"Entry ${v._2}:")
    println(s"\tPath: ${v._1._2}")
    println(s"\tSize: ${v._1._1} MB")
    println()
  })
}
