# syntax=docker/dockerfile:1

FROM tomcat:9
LABEL maintainer='olga.a.grigorieva@gmail.com'
ADD ./target/restfulSpring.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]

#WORKDIR /backend

#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src

#CMD ["./mvnw", "spring-boot:run"]