FROM mysql:5.5

VOLUME /mySQL
ENV MYSQL_ROOT_PASSWORD=root

ENV MYSQL_DATABASE=computer-database-db

# Add sql file in entryPoint to populate DB in docker run
ADD ./1-SCHEMA.sql /docker-entrypoint-initdb.d/1-SCHEMA.sql
ADD ./2-PRIVILEGES.sql /docker-entrypoint-initdb.d/2-PRIVILEGES.sql
ADD ./3-ENTRIES.sql /docker-entrypoint-initdb.d/3-ENTRIES.sql

#docker run --network=network-test -i --name mysql-test mysql-test

