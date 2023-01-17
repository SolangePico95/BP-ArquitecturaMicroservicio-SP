FROM openjdk:11
COPY "./target/microservicios-0.0.1-SNAPSHOT.jar" "microservicio.jar"
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "microservicio.jar" ]