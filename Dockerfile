FROM mlaccetti/docker-oracle-java8-ubuntu-16.04

MAINTAINER Oleg Romanov <oleromd@gmail.com>

EXPOSE 8080

COPY ./ ./home/yadro-internship/

WORKDIR home/yadro-internship
RUN apt-get update && apt-get install net-tools
RUN ./gradlew :example:getJar
CMD ["java", "-jar", "./example/build/libs/example-1.0-SNAPSHOT.jar"]