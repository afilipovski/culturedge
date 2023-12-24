FROM maven:3.9.6 AS build

WORKDIR /app

COPY bin ./bin
COPY data ./data
COPY images ./images
COPY src ./src
COPY config.json .

COPY /target/culturedge-0.0.1-SNAPSHOT.jar ./target/culturedge

FROM amazoncorretto:21-alpine3.16-jdk

WORKDIR /app

COPY --from=build /app .

CMD java -jar ./target/culturedge

EXPOSE 8080