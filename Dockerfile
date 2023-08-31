FROM openjdk:17

WORKDIR /app

COPY target/vending-machine-backend-1-SNAPSHOT.jar /app/vending-machine-backend-1-SNAPSHOT.jar

EXPOSE 8080

ARG mongodb_uri
ARG mongodb-database

ENV MONGODB_URI=$mongodb_uri
ENV MONGODB_DATABASE=$mongodb-database

CMD ["java", "-jar", "vending-machine-backend-1-SNAPSHOT.jar"]