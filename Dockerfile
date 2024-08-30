FROM openjdk:17-jdk-slim

# Установка psql
RUN apt-get update && apt-get install -y postgresql-client

WORKDIR /app
COPY target/expense-tracker-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
