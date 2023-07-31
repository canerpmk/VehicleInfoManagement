<<<<<<< HEAD
FROM ubuntu:latest
LABEL authors="hasan"

ENTRYPOINT ["top", "-b"]
=======
FROM openjdk:latest
WORKDIR /app
EXPOSE 8080
COPY target/Sahibinden-0.0.1-SNAPSHOT.jar Sahibinden.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Sahibinden.jar"]
>>>>>>> 4fb942c (Initial commit)
