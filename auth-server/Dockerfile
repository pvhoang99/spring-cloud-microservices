FROM openjdk:11
WORKDIR /microservices
COPY ./target /microservices/auth-server
EXPOSE 8082
CMD ["java","-jar","auth-server.jar"]
