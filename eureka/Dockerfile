# Build app from maven
FROM maven:3-amazoncorretto-23-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Run app
FROM openjdk:23-jdk-slim
VOLUME /tmp
EXPOSE 8761
COPY --from=build /app/target/*.jar /app/eureka.jar
ENTRYPOINT ["java", "-jar", "/app/eureka.jar"]