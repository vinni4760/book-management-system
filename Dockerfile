FROM openjdk:22-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","jar","app.jar"]