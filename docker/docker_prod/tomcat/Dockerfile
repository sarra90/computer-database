FROM tomcat:7.0-jre8
#RUN mkdir -p /cdb

# /usr/local/tomcat/webapps is already exist
#RUN mkdir /usr/local/tomcat/webapps

#comment in no jenkins
VOLUME ["CDB_Volume:/cdb"]


#docker run --network=network-prod -it -p 80:80 --name tomcat-prod 
-v /home/excilys/workspace/computer-database/target:/usr/local/tomcat/webapps tomcat

