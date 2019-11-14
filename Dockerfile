FROM openjdk:8-jdk-alpine
COPY ./target/world-0.0.1-SNAPSHOT.jar /usr/src/world/
WORKDIR /usr/src/world
EXPOSE 8080
CMD ["java", "-jar", "world-0.0.1-SNAPSHOT.jar"]
