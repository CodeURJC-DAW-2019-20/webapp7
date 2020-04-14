#Image from https://hub.docker.com/_/maven
#Image from https://hub.docker.com/_/openjdk


## BUILD SPRING APP ##

FROM adoptopenjdk/openjdk13:latest

COPY ./app/backend.jar /usr/src/backend
COPY ./images/ /usr/src/backend/images
WORKDIR /usr/src/backend
CMD ["java", "-jar", "backend.jar"]

