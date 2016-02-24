package in.tuxdna

import akka.actor._
import com.typesafe.config.ConfigFactory

import akka.actor.{ActorSelection, Actor, ActorRef}

import scala.util.Random

case object Start

case object Stop

class NewsPublisherActor(receiver: ActorSelection) extends Actor {
  val newsItems = List(
    "News Item 1",
    "News Item 2",
    "News Item 3"
  )

  def receive = {
    case Start =>
      println("Start generating news items")
      for (i <- 0 until 20) {
        val item = newsItems(Random.nextInt(newsItems.size))
        println(s"Send news item to ${receiver}")
        receiver ! item
      }

    case Stop =>
      println("Stop generating news items")
      receiver ! Stop
  }
}

object NewsPublisherService extends App {
  val config = ConfigFactory.load.getConfig("NewsPublisherService")
  val actorSystem = ActorSystem("NewsSystem", config)

  val receiverSelection = actorSystem.actorSelection("akka.tcp://NewsSystem@127.0.0.1:2558/user/NewsReceiver")
  println(s"Intializing publisher with selection: ${receiverSelection}")
  val publisher = actorSystem.actorOf(Props(new NewsPublisherActor(receiverSelection)))


  publisher ! Start
  Thread.sleep(1000)
  publisher ! Stop

  Thread.sleep(1000)
  actorSystem.shutdown()
}

class NewsReceiver extends Actor {
  def receive = {
    case news: String =>
      println(s"Received Item: ${news}")
    case Stop =>
      println("Shutdown my actor system")
      context.system.shutdown()
  }
}

object NewsReceiverService extends App {
  val config = ConfigFactory.load.getConfig("NewsReceiverService")
  val actorSystem = ActorSystem("NewsSystem", config)

  val receiver = actorSystem.actorOf(Props[NewsReceiver], "NewsReceiver")

  println(s"Wait 100 seconds with receiver as : ${receiver}")

  actorSystem.awaitTermination()
}