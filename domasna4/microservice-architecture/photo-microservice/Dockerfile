FROM maven:3.9.6 AS build

WORKDIR /app

COPY images ./images
COPY src ./src

COPY pom.xml .

RUN mvn package

FROM amazoncorretto:21-alpine3.16-jdk

WORKDIR /app

COPY --from=build /app .

CMD ["java", "-jar", "./target/photo-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 2222