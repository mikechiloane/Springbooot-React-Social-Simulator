# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17 as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Stage 2: Create the final image with OpenJDK 17
FROM adoptopenjdk:17-jdk-hotspot
COPY --from=build /home/app/target/location-0.0.1-SNAPSHOT.jar /usr/local/lib/location-0.0.1-SNAPSHOT.jar

# Expose port 8084 for the application
EXPOSE 8084

# Set the entry point as the Spring Boot application JAR file
ENTRYPOINT ["java", "-jar", "/usr/local/lib/location-0.0.1-SNAPSHOT.jar"]
