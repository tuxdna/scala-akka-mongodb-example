package in.tuxdna.db

import com.mongodb._
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Database {

  val collection = connect()

  def connect(): DBCollection = {
    val mongo = new MongoClient("localhost", 27017);
    val db = mongo.getDB("akka")
    val stocksCollection = db.getCollection("stocks")
    stocksCollection
  }

  def findAllTickers(): Future[List[DBObject]] = {
    Future {
      val cursor = collection.find()
      val objects = ArrayBuffer[DBObject]()
      while (cursor.hasNext) {
        val e = cursor.next()
        objects.append(e)
      }
      cursor.close()
      objects.toList
    }
  }

  def findTicker(ticker: String): Future[Option[DBObject]] = Future {
    val q = new BasicDBObject()
    q.put("Ticker", ticker)
    val cursor = Database.collection.find(q)
    if (cursor.hasNext) {
      val e = cursor.next
      cursor.close()
      Some(e)
    } else {
      None
    }

  }

}