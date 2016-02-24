BBC News Data

 * http://mlg.ucd.ie/datasets/bbc.html

Install SBT from

 * http://www.scala-sbt.org/0.13/docs/Installing-sbt-on-Linux.html



Loading data into mongoDB

 * https://dzone.com/articles/building-rest-service-scala


```
wget http://jsonstudio.com/wp-content/uploads/2014/02/stocks.zip
unzip -c stocks.zip | mongoimport --db akka --collection stocks
```


