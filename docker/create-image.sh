if ! which java > /dev/null; then
   echo -e "JAVA not found. It will install shortly\c"
   sudo apt-get install openjdk-13-jdk
fi
if ! which mvn > /dev/null; then
   echo -e "Maven not found. It will install shortly\c"
   sudo apt-get install maven
fi
mkdir ../backend/src/main/resources/static/new/
cd ../ngVoluntaweb

if ! which node > /dev/null; then
   echo -e "NodeJS not found. It will install shortly\c"
   sudo apt install nodejs
   sudo apt install npm
fi
if ! which ng > /dev/null; then
   echo -e "AngularCLI not found. It will install shortly\c"
   sudo npm install -g @angular/cli
fi

sudo npm install > /dev/null
sudo ng build --baseHref=/new/

cd dist/ngVoluntaweb
cp -r . ../../../backend/src/main/resources/static/new/.



cd ../../../backend
mvn clean install
cd target
mkdir ../../docker/app/
mv backend_src-0.0.1-SNAPSHOT.jar ../../docker/app/
cd ../../docker/app/
mv backend_src-0.0.1-SNAPSHOT.jar backend.jar
cd ..
sudo docker build -t dserranoc/voluntaweb .
sudo docker push dserranoc/voluntaweb
