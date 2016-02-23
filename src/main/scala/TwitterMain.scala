//import akka.actor.ActorSystem
//import akka.stream.ActorMaterializer
//import akka.stream.scaladsl.Source
//
//final case class Author(handle: String)
//
//final case class Hashtag(name: String)
//
//final case class Tweet(author: Author, timestamp: Long, body: String) {
//  def hashtags: Set[Hashtag] =
//    body.split(" ").collect { case t if t.startsWith("#") => Hashtag(t) }.toSet
//}
//
//val akka = Hashtag("#akka")
//
//object TwitterMain extends App {
//
//  implicit val system = ActorSystem("reactive-tweets")
//  implicit val materializer = ActorMaterializer()
//
//  val tweets: Source[Tweet, Unit] == something
//
//  val authors: Source[Author, Unit] =
//    tweets
//      .filter(_.hashtags.contains(akka))
//      .map(_.author)
//
//  authors.runWith(Sink.foreach(println))
//
//}
//
