FROM java:8
EXPOSE 8080

VOLUME /tmp
ADD springboot-docker.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]