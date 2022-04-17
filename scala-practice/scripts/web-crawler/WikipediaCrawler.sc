import mainargs.main

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration.Duration.Inf

@main def main(wikiTitle: String, depth: Int): Unit = {
  println(
    s"Crawling using ${Runtime.getRuntime.availableProcessors()} processors"
  )
  Thread.sleep(1000)
  fetchAllLinks(wikiTitle, depth)
    .foreach(println)
}

def fetchAllLinks(start: String, depth: Int): Set[String] = {
  var seen = Set(start)
  var current = Set(start)
  for (i <- Range(0, depth)) {
    val futures = for (title <- current) yield Future { fetchLinks(title) }
    val nextTitleLists = futures.map(Await.result(_, Inf))
    current = nextTitleLists.flatten.filter(!seen.contains(_))
    seen = seen ++ current
  }
  seen
}

def fetchLinks(title: String): Seq[String] = {
  println(s"requesting... $title")
  val resp = requests.get(
    "https://en.wikipedia.org/w/api.php",
    params = Seq(
      "action" -> "query",
      "titles" -> title,
      "prop" -> "links",
      "format" -> "json"
    )
  )

  for {
    page <- ujson.read(resp)("query")("pages").obj.values.toSeq
    links <- page.obj.get("links").toSeq
    link <- links.arr
  } yield link("title").str
}
