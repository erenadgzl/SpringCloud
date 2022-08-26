FROM openjdk:19-jdk-alpine
COPY target/EurekaServer-0.0.1-SNAPSHOT.jar DiscoveryService.jar
ENTRYPOINT ["java", "-jar", "DiscoveryService.jar"]