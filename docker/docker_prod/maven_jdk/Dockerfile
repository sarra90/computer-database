FROM maven:3-jdk-8

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

#VOLUME CDB_Volume


CMD mvn package -Dmaven.test.skip=true


