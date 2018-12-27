FROM java:8
EXPOSE 8080
ADD target/contabil-0.0.1-SNAPSHOT.jar contabil-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "contabil-0.0.1-SNAPSHOT.jar"]