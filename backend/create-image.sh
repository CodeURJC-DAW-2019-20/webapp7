#!/bin/bash


path_docker=$(pwd)
path_project=$(pwd)
path_jar=$path_project/target


echo "Compiling app"
cd $path_project
./mvnw package && java -jar target/gs-spring-boot-docker-0.1.0.jar

cd $path_docker
mv $path_jar/backend-0.0.1-SNAPSHOT.jar .
rm backend.jar
mv backend-0.0.1-SNAPSHOT.jar backend.jar

echo "Creating docker image"
docker build -t theroxd4n/voluntaweb .
docker push theroxd4n/voluntaweb

