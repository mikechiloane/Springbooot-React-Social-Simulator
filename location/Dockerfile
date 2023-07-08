FROM eclipse-temurin:17
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
COPY --from=build /target/location-0.0.1-SNAPSHOT.jar /app/location-0.0.1-SNAPSHOT.jar

EXPOSE 8084
ENTRYPOINT ["java","-jar", "location-0.0.1-SNAPSHOT.jar"]
