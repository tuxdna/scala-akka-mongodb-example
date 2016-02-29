# VERSION 1.0

# the base image is a trusted ubuntu build with java 7 (https://index.docker.io/u/dockerfile/java/)
FROM webdizz/centos-java8

MAINTAINER Saleem Ansari, tuxdna@gmail.com

# we need this because the workdir is modified in dockerfile/java
WORKDIR /

# run the (java) server as the daemon user
USER daemon

# copy the locally built fat-jar to the image
ADD target/scala-akka-mongodb-example-0.0.1-SNAPSHOT-allinone.jar /app/server.jar

# the server binds to 8080 - expose that port
EXPOSE 8082

# run the server when a container based on this image is being run
CMD [ "java", "-cp", "/app/server.jar", "in.tuxdna.services.GeoIpAPI" ]
