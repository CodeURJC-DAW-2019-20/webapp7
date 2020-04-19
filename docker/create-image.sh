
#Angular compile project
cd ../ngVoluntaweb

sudo docker run -it --rm --name ngVoluntaweb -v "$PWD":/usr/src/app -w /usr/src/app node:12.16.1 /bin/bash -c "cd /usr/src/app && npm install > /dev/null && npm i -g @angular/cli > /dev/null && ng build --baseHref=/new/"


#Move angular project to maven project
cd dist/ngVoluntaweb
cp -r . ../../../backend/src/main/resources/static/new/.


#Maven compile project
cd ../../../backend
sudo docker run -it --rm --name voluntaweb -v "$PWD":/usr/src/app -w /usr/src/app maven:3.6.3-openjdk-15 mvn clean install

#Build image
cd target
mkdir ../../docker/app/
mv backend_src-0.0.1-SNAPSHOT.jar ../../docker/app/
cd ../../docker/app/
mv backend_src-0.0.1-SNAPSHOT.jar backend.jar
cd ..
sudo docker build -t theroxd4n/voluntaweb .

#Docker-compose!
sudo docker push theroxd4n/voluntaweb
