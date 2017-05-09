FROM maven:3-jdk-8

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

VOLUME CDB_Volume
CMD mvn test

#1 Build an image from a Dockerfile
	#-t 
	#docker build -t mvn-jdk8 .
#2 Build the container from image 
	#--network=name-of-network link the container from the network
	#--name name-of-container
	#-v source-hote:workdir
	#mvn-jdk8 l image 
	#docker run --network=network-test --name mvn-test -v /home/excilys/workspace/computer-database:/usr/src/app mvn-jdk8 
