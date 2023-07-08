FROM eclipse-temurin:17

WORKDIR /app

COPY target/gateway-0.0.1-SNAPSHOT.jar /app/gateway-0.0.1-SNAPSHOT.jar

EXPOSE 8080

# Set the entry point command for the container
CMD ["java", "-jar","-Dserver.port=8080", "gateway-0.0.1-SNAPSHOT.jar"]
