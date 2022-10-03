####################JAVA################
FROM openjdk:11.0.16 as java
FROM maven:3.8.1
VOLUME /root/.m2/repository
VOLUME /microservices
ENTRYPOINT ["/bin/bash", "/microservices/run.sh"]