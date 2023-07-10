FROM maven:3.8.4-openjdk-17 as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-jdk
COPY --from=build /home/app/target/auth-server-0.0.1-SNAPSHOT.jar /usr/local/lib/auth-server-0.0.1-SNAPSHOT.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/local/lib/auth-server-0.0.1-SNAPSHOT.jar"]
