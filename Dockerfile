FROM java:8
ADD target/demoapi-0.0.1-SNAPSHOT.jar prometheus-demo-spring-boot.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","prometheus-demo-spring-boot.jar"]
