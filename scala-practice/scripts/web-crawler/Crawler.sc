import $ivy.`org.asynchttpclient:async-http-client:2.5.2`
import mainargs.main

val asyncHttpClient = org.asynchttpclient.Dsl.asyncHttpClient()
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration.Duration.Inf

/** Crawls wikipedia pages up to a specified depth
  *
  * @param title the title of a wikipedia page
  * @param depth the maximum distance to crawl to from the initial page
  */
@main def main(title: String, depth: Int): Unit = {
  println(
    s"Crawling using ${Runtime.getRuntime.availableProcessors()} processors"
  )
}

def fetchLinksRec(title: String, depth: Int): Set[String] = {
  def rec(
      current: Set[String],
      seen: Set[String],
      recDepth: Int
  ): Set[String] = {
    if (recDepth >= depth) seen
    else {
      val futures = for (title <- current) yield Future {
        fetchLinks(title)
      }
      val nextTitles = futures.flatMap(Await.result(_, Inf))
      rec(
        nextTitles.filter(!seen.contains(_)),
        seen ++ nextTitles,
        recDepth + 1
      )
    }
  }

  rec(Set(title), Set(title), 0)
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
