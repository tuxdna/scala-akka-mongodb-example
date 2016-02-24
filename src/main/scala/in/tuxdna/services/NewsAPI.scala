package in.tuxdna.services

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import in.tuxdna.db.Database

object NewsAPI extends App {
  implicit val system = ActorSystem("NewsAPISystem")
  implicit val executor = system.dispatcher
  val config = ConfigFactory.load()
  val logger = Logging(system, getClass)
  implicit val materializer = ActorMaterializer()


  val routes = {
    logRequestResult("NewsAPI") {
      pathPrefix("getAllNews") {
        get {
          complete {
            for (input <- Database.loadAllNews()) yield input.toString
          }
        }
      }
    }
  }

  Http().bindAndHandle(routes, config.getString("newsAPI.http.interface"), config.getInt("newsAPI.http.port"))
}
