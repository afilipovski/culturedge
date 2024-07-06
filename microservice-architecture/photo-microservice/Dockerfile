FROM maven:3.9.6 AS build

WORKDIR /app

COPY src ./src
COPY static ./static

COPY pom.xml .

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine3.16-jdk

WORKDIR /app

RUN mkdir images

COPY --from=build /app .

CMD ["java", "-jar", "./target/photo-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 2222