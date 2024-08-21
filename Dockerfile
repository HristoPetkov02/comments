FROM amazoncorretto:21-alpine
WORKDIR comments-service
COPY rest/target/rest-0.0.1-SNAPSHOT.jar /comments-service/comments.jar
ENTRYPOINT ["java","-jar","/comments-service/comments.jar"]