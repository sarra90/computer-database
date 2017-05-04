FROM java:8 

# Install maven
RUN apt-get update
RUN apt-get install -y maven


WORKDIR /usr/src/app

ADD pom.xml /usr/src/app/pom.xml

RUN ["mvn", "dependency:resolve"]
RUN ["mvn","validate"]
RUN ["mvn", "compile"]
RUN ["mvn", "test"]


