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
  println(Await.result(fetchAllLinksAsync(title, depth, 20), Inf))
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

def fetchAllLinksAsync(startTitle: String, maxDepth: Int, maxConcurrency: Int): Future[Set[String]] = {
  def rec(current: Seq[(String, Int)], seen: Set[String]): Future[Set[String]] = {
    pprint.log((maxDepth, current.size, seen.size))
    if (current.isEmpty) Future.successful(seen)
    else {
      val (throttled, remaining) = current.splitAt(maxConcurrency)
      val futures =
        for ((title, depth) <- throttled)
          yield fetchLinksAsync(title).map((_, depth))

      Future.sequence(futures).map{nextTitleLists =>
        val flattened = for{
          (titles, depth) <- nextTitleLists
          title <- titles
          if !seen.contains(title) && depth < maxDepth
        } yield (title, depth + 1)
        rec(remaining ++ flattened, seen ++ flattened.map(_._1))
      }.flatten
    }
  }
  rec(Seq(startTitle -> 0), Set(startTitle))
}

