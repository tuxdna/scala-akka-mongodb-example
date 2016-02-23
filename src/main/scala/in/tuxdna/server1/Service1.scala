package in.tuxdna.server1

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import in.tuxdna.db.Database


object Service1 extends App {

  implicit val system = ActorSystem("Streams")
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()
  val config = ConfigFactory.load()
  val logger = Logging(system, getClass)

  val routes = {
    logRequestResult("service1") {
      pathPrefix("getAllTickers") {
        get {
          complete {
            for {
              input <- Database.findAllTickers
            } yield {
              input.toString
            }
          }
        }
      }
      pathPrefix("ticker") {
        (get & path(Segment)) { ticker =>
          complete {
            // if we find the query parameter
            case Some(ticker) => {

              // query the database
              val ticker = Database.findTicker(ticker)

              // use a simple for comprehension, to make
              // working with futures easier.
              for {
                t <- ticker
              } yield {
                t match {
                  case Some(t) => t.toString
                  case None => HttpResponse(status = OK)
                }
              }
            }
          }
        }
      }
    }
  }

  Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port1"))
}
