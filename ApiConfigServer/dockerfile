FROM openjdk:19-jdk-alpine
VOLUME /tmp
COPY target/ApiConfigServer-0.0.1-SNAPSHOT.jar ConfigServer.jar
ENTRYPOINT ["java", "-jar", "ConfigServer.jar"]