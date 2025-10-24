# 1️⃣ Use official OpenJDK 17 as base image
FROM eclipse-temurin:17-jdk-alpine

# 2️⃣ Set working directory inside the container
WORKDIR $APP_HOME

# 3️⃣ Copy Maven-built jar into the container
COPY app/*.jar $APP_HOME/app.jar

# 4️⃣ Expose port 8080
EXPOSE 8080

# 5️⃣ Command to run the jar
ENTRYPOINT ["java","-jar","app.jar"]
