FROM eclipse-temurin:17
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17
COPY --from=build /home/app/target/location-0.0.1-SNAPSHOT.jar /usr/local/lib/location-0.0.1-SNAPSHOT.jar

EXPOSE 8084
ENTRYPOINT ["java","-jar", "/usr/local/lib/location-0.0.1-SNAPSHOT.jar"]
