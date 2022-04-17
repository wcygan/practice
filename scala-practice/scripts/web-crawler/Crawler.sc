import $ivy.`org.asynchttpclient:async-http-client:2.5.2`
import mainargs.main

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration.Duration.Inf

val asyncHttpClient = org.asynchttpclient.Dsl.asyncHttpClient()

/** Crawls wikipedia pages up to a specified depth
 *
 * Usage: "amm Crawler.sc Illinois 2"
 *
 * @param title the title of a wikipedia page
 * @param depth the maximum distance to crawl to from the initial page
 */
@main def main(title: String, depth: Int): Unit = {
  println(Await.result(fetchAllLinksAsync(title, depth), Inf))
}

def fetchLinksAsync(title: String)(implicit ec: ExecutionContext): Future[Seq[String]] = {
  val p = Promise[String]
  val listenableFut = asyncHttpClient.prepareGet("https://en.wikipedia.org/w/api.php")
    .addQueryParam("action", "query").addQueryParam("titles", title)
    .addQueryParam("prop", "links").addQueryParam("format", "json")
    .execute()

  listenableFut.addListener(() => p.success(listenableFut.get().getResponseBody), null)
  val scalaFut: Future[String] = p.future
  scalaFut.map { responseBody =>
    for {
      page <- ujson.read(responseBody)("query")("pages").obj.values.toSeq
      links <- page.obj.get("links").toSeq
      link <- links.arr
    } yield link("title").str
  }(global)
}

def fetchAllLinksAsync(startTitle: String, depth: Int): Future[Set[String]] = {
  def rec(current: Set[String], seen: Set[String], recDepth: Int): Future[Set[String]] = {
    if (recDepth >= depth) Future.successful(seen)
    else {
      val futures = for (title <- current) yield fetchLinksAsync(title)
      Future.sequence(futures).map { nextTitleLists =>
        val nextTitles = nextTitleLists.flatten
        rec(nextTitles.filter(!seen.contains(_)), seen ++ nextTitles, recDepth + 1)
      }.flatten
    }
  }

  rec(Set(startTitle), Set(startTitle), 0)
}