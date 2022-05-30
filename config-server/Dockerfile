FROM openjdk:11
WORKDIR /microservices
COPY ./target /microservices/config-server
EXPOSE 8888
CMD ["java","-jar","config-server.jar"]
