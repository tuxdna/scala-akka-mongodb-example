package in.tuxdna.db

import com.mongodb._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Database {

  val dbHost = "localhost"
  val dbPort = 27017
  val dbName = "akka"
  val newsCollectionName = "news"
  val stocksCollectionName = "stocks"

  def getDB() = {
    val mongo = new MongoClient(dbHost, dbPort)
    mongo.getDB(dbName)
  }

  def newsCollection(): DBCollection = {
    getDB().getCollection(newsCollectionName)
  }

  def loadAllNews(): Future[List[DBObject]] = {
    Future {
      val cursor = newsCollection().find()
      val objects = ArrayBuffer[DBObject]()
      while (cursor.hasNext) {
        val e = cursor.next()
        objects.append(e)
      }
      cursor.close()
      objects.toList
    }
  }

  def saveNewsItem(item: String): Unit = {
    val document = new BasicDBObject()
    document.put("item", item)
    newsCollection().insert(document)
  }

  def stocksCollection(): DBCollection = {
    getDB().getCollection(stocksCollectionName)
  }

  def findAllTickers(): Future[List[DBObject]] = {
    Future {
      val cursor = stocksCollection.find()
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
    val cursor = stocksCollection().find(q)
    if (cursor.hasNext) {
      val e = cursor.next
      cursor.close()
      Some(e)
    } else {
      None
    }
  }

}