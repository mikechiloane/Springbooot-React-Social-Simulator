FROM eclipse-temurin:17

WORKDIR /app

COPY target/location-0.0.1-SNAPSHOT.jar /app/location-0.0.1-SNAPSHOT.jar

EXPOSE 8084

# Set the entry point command for the container
CMD ["java", "-jar","-Dserver.port=8084", "location-0.0.1-SNAPSHOT.jar"]
