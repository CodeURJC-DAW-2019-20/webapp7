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

sudo npm install --silent
sudo npm run build

cd dist/ngVoluntaweb
cp . ../../../backend/src/main/resources/static/new/.



cd ../../../backend
mvn clean install
cd target
mv backend_src-0.0.1-SNAPSHOT.jar ../../docker/app/backend.jar
cd ..
cd ..
cd docker
docker build -t dserranoc/voluntaweb .
docker push dserranoc/voluntaweb
