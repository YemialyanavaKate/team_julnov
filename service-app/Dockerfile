FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/service-app-0.0.1.jar /app/service-app-0.0.1.jar

ENTRYPOINT ["java", "-jar", "service-app-0.0.1.jar"]