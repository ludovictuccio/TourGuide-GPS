FROM openjdk:11
EXPOSE 9002
ARG JAR_FILE=build/libs/gps-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]