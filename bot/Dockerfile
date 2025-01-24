FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/bot-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]
