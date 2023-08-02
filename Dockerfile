
WORKDIR /app
EXPOSE 8080
COPY target/Sahibinden-0.0.1-SNAPSHOT.jar Sahibinden.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Sahibinden.jar"]

