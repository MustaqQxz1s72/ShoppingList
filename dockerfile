# OpenJDK 17 
FROM eclipse-temurin:17-jdk-alpine

# working directory inside the container
ENV APP_HOME=/app
WORKDIR $APP_HOME

ARG BUILD_SOURCE=target
COPY ${BUILD_SOURCE}/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

