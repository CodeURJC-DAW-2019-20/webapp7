cd ../backend
mvn clean install
cd target
mv backend_src-0.0.1-SNAPSHOT.jar ../../docker/app/backend.jar
cd ..
cd ..
cd docker
docker build -t dserranoc/voluntaweb .
docker push dserranoc/voluntaweb
