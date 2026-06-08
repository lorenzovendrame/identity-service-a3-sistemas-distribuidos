FROM maven:3.9.6-eclipse-temurin-25-alpine AS build
LABEL authors="Lorenzo"

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-alpine

WORKDIR /app

COPY --from=build /app/target/identity-service.jar identity-service.jar

RUN apk add --no-cache tzdata
ENV TZ=America/Sao_Paulo

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "identity-service.jar"]