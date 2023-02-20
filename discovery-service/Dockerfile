FROM openjdk:11
WORKDIR /microservices
COPY ./target /microservices/auth-server
EXPOSE 8761
CMD ["java","-jar","discovery-server.jar"]
