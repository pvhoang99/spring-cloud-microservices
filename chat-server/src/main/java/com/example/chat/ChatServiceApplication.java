package com.example.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringCloudApplication
@EnableOAuth2Sso
@EnableFeignClients
@EnableConfigurationProperties
@EnableNeo4jRepositories(value = {"com.example.chat.dao.repository"})
@EntityScan(value = {"com.example.chat.dao.entity"})
public class ChatServiceApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(ChatServiceApplication.class, args);

  }

}
