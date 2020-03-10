#!/bin/bash


path_docker=$(pwd)
path_project=$(pwd)
path_jar=$path_project/target


cd $path_docker
mv $path_jar/backend_src-0.0.1-SNAPSHOT.jar .
rm backend.jar
mv backend_src-0.0.1-SNAPSHOT.jar backend.jar

echo "Creating docker image"
docker build -t theroxd4n/voluntaweb .
docker push theroxd4n/voluntaweb

