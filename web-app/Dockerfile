FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/web-app-0.0.1.jar /app/web-app-0.0.1.jar

ENTRYPOINT ["java", "-jar", "web-app-0.0.1.jar"]