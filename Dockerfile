FROM amazoncorretto:8
LABEL maintainer="sr.funk.sensei@gmail.com"
COPY target/rps-0.0.1-SNAPSHOT.jar rps.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/rps.jar"]