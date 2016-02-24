How to build?

```
mvn clean compile assembly:assembly
```

How to run?


```
java -cp target/scala-akka-mongodb-example-0.0.1-SNAPSHOT-allinone.jar  Main
http://localhost:8080/hello
```

That was just a CLI example to demonstrate how to build and execute the services.


Extra / External dependecies:

 * MongoDB
 * Free GeoIP Service ( external service http://freegeoip.net/ )


With default settings, on a single machine following URLs ( services ) should work.


NewsAPI

```
java -cp target/scala-akka-mongodb-example-0.0.1-SNAPSHOT-allinone.jar  in.tuxdna.services.NewsAPI
wget http://localhost:8080/getAllNews
```


StockAPI


```
java -cp target/scala-akka-mongodb-example-0.0.1-SNAPSHOT-allinone.jar  in.tuxdna.services.StockAPI
wget http://localhost:8081/ticker/GOOG
```


GeoIpAPI


```
java -cp target/scala-akka-mongodb-example-0.0.1-SNAPSHOT-allinone.jar  in.tuxdna.services.GeoIpAPI
wget http://localhost:8082/ip/8.8.8.8
```


News Publisher / Subscriber

Firrst, start the Reciever


```
java -cp target/scala-akka-mongodb-example-0.0.1-SNAPSHOT-allinone.jar  in.tuxdna.services.NewsReceiverService
```

Now start the Publisher


```
java -cp target/scala-akka-mongodb-example-0.0.1-SNAPSHOT-allinone.jar  in.tuxdna.services.NewsPublisherService
```

This will establish Actors communicating over TCP, which we can distribute on different hosts.



TBD: Convert into an AtomicApp ( using Nulecule spec )



