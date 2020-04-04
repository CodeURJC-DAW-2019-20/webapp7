#Image from https://hub.docker.com/_/maven
#Image from https://hub.docker.com/_/openjdk

FROM adoptopenjdk/openjdk13:latest

COPY ./app/backend.jar /usr/src
COPY ./images/ /usr/src/images
WORKDIR /usr/src
CMD ["java", "-jar", "backend.jar"]
