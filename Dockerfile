FROM webdizz/centos-java8

MAINTAINER Saleem Ansari <tuxdna@fedoraproject.org>

USER daemon

RUN mkdir -p /scala-app

WORKDIR /scala-app

# copy the locally built fat-jar to the image
ADD target/scala-akka-mongodb-example-0.0.1-SNAPSHOT-allinone.jar /scala-app/server.jar

# the server binds to 8080 - expose that port
EXPOSE 8080

# run the server when a container based on this image is being run
# CMD [ "java", "-cp", "/app/server.jar", "in.tuxdna.services.StockAPI" ]

CMD /scala-app/entrypoint.sh
