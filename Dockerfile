FROM mlaccetti/docker-oracle-java8-ubuntu-16.04

MAINTAINER Oleg Romanov <oleromd@gmail.com>

# default: порт 8080 будет слушать приложение внутри контейнера
EXPOSE 8080

COPY ./ ./home/yadro-internship/

WORKDIR home/yadro-internship
RUN ./gradlew :example:getJar
RUN java -jar ./example/build/libs/example-1.0-SNAPSHOT.jar