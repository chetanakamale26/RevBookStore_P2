from openjdk:17
EXPOSE 8080
ADD target/oauth2server-2.0.war oauth2server.war
ENTRYPOINT ["java","-jar","/oauth2server.war"]