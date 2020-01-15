FROM ubuntu:latest

MAINTAINER Gab "gab.berteloot@gmail.com"

RUN apt-get update && apt-get install -y openjdk-8-jdk

ENV version=docker
ENV dbuser
ENV dbpass
ENV jdbcurl

WORKDIR /usr/local/bin/

ADD target/pma-app.jar .

# CMD ["/bin/bash"]

ENTRYPOINT ["java", "-jar", "pma-app.jar"]
