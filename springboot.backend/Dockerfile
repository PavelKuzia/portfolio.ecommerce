# build jar file #
FROM maven:3.9.9-eclipse-temurin-23 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests -q

FROM openjdk:23-jdk

WORKDIR /app

COPY --from=build app/target/portfolio.ecommerce.backend.jar backend.jar

EXPOSE 8005

ENV ENV_BACKEND_PORT=8005

ENV ENV_PORT_RDS=5432
ENV ENV_DB_NAME=postgres
ENV ENV_STAGE=prod
ENV VERSION=0.0.1
ENV ANGULAR_PORT=4200
ENV ANGULAR_URL=http://localhost

CMD ["java", "-jar", "backend.jar"]