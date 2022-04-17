package io.wcygan.examples.concurrent

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext.global
import scala.concurrent.duration.Duration.Inf
import scala.concurrent.{
  Await,
  ExecutionContext,
  ExecutionContextExecutorService,
  Future
}

object FuturesAndGlobalContext {
  final val USE_GLOBAL_CONTEXT = false

  val singleThreaded: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(
      Executors.newFixedThreadPool(1)
    )

  def main(args: Array[String]): Unit = {
    // could also use ExecutionContext.Implicit.global
    val exec = if (USE_GLOBAL_CONTEXT) global else singleThreaded
    val start = System.nanoTime()

    // spawn the futures on an executor
    val futures = for (i <- Range(0, 10)) yield Future {
      Thread.sleep(100)
      i
    }(exec)

    // wait for the futures to finish
    futures.foreach(x => {
      val i = Await.result(x, Inf)
      println(s"${x.isCompleted} Hello! $i")
    })

    println(s"Finished ${(System.nanoTime() - start) / 1e9d} after seconds!")
  }
}
