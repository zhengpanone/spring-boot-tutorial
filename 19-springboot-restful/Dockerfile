## ---------- build stage ----------
#FROM maven:3.9.9-eclipse-temurin-21 AS build
#WORKDIR /app
#COPY . .
#RUN mvn clean package -pl 19-springboot-restful -am -DskipTests
#
## ---------- run stage ----------
#FROM eclipse-temurin:21-jre
#WORKDIR /app
#COPY --from=build /app/19-springboot-restful/target/19-springboot-restful-0.0.1-SNAPSHOT.jar app.jar

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY ./target/19-springboot-restful-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]