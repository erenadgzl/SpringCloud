FROM openjdk:19-jdk-alpine
VOLUME /tmp
COPY target/ApiGateway-0.0.1-SNAPSHOT.jar ApiGateway.jar
ENTRYPOINT ["java", "-jar", "ApiGateway.jar"]