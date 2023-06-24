FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/myspringapp.jar app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]
