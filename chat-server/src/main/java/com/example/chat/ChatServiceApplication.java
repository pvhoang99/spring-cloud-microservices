package com.example.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.neo4j.annotation.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringCloudApplication
@EnableOAuth2Sso
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties
@EnableNeo4jRepositories
public class ChatServiceApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(ChatServiceApplication.class, args);

  }

}
