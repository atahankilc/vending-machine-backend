FROM maven:3.8.3-openjdk-17 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml -DskipTests clean package

FROM openjdk:17
COPY --from=build /usr/src/app/target/vending-machine-backend-1-SNAPSHOT.jar /usr/app/vending-machine-backend-1-SNAPSHOT.jar
EXPOSE 8080

ENV MONGODB_URI ""
ENV MONGODB_DATABASE ""

ENTRYPOINT ["java","-jar","/usr/app/vending-machine-backend-1-SNAPSHOT.jar"]