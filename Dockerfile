FROM maven:3-eclipse-temurin-23-alpine AS build

WORKDIR /app

COPY ./pom.xml .
RUN mvn dependency:resolve

COPY . .
RUN mvn package -DskipTests

FROM openjdk:23-jdk-slim as final

WORKDIR /

COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]