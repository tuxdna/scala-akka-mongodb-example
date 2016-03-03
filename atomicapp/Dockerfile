FROM projectatomic/atomicapp:0.4.2

MAINTAINER Saleem Ansari <tuxdna@fedoraproject.org>

LABEL io.projectatomic.nulecule.specversion="0.0.2"
LABEL io.projectatomic.nulecule.providers="kubernetes, openshift, docker"
LABEL Build="docker build --rm --tag test/scala-akka-atomicapp ."

ADD /Nulecule /Dockerfile README.md /application-entity/
ADD /artifacts /application-entity/artifacts

