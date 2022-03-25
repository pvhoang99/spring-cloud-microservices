package com.example.auth;

import com.example.auth.config.security.client.ClientDetailRepository;
import com.example.auth.dao.model.OauthClientDetailEntity;
import com.example.auth.dao.model.RoleEntity;
import com.example.auth.dao.model.UserEntity;
import com.example.auth.dao.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class AuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
  }

  @Bean
  public CommandLineRunner init(ClientDetailRepository oauthClientDetailRepository,
      UserRepository userRepository) {
    return args -> {
      if (!oauthClientDetailRepository.existsByClientId("hoang")) {
        OauthClientDetailEntity clientDetailEntity = new OauthClientDetailEntity();
        clientDetailEntity.setClientId("hoang");
        clientDetailEntity.setAuthorizedGrantTypes(
            "password,authorization_code,refresh_token,client_credentials");
        clientDetailEntity.setAutoApprove(true);
        clientDetailEntity.setClientSecret("1");
        clientDetailEntity.setResourceIds("hoang,auth-server,order-server,chat-server");
        clientDetailEntity.setScope("read,write,server");
        clientDetailEntity.setWebServerRedirectUri(
            "http://localhost:8080/oauth2/callback/hoang,http://localhost:3000");
        oauthClientDetailRepository.save(clientDetailEntity);
      }
      if (!userRepository.existsByUsername("hoangpv")) {
        RoleEntity roleEntity = new RoleEntity("USER", "User");
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName("Phạm Việt Hoàng");
        userEntity.setIsActive(true);
        userEntity.setPassword("1");
        userEntity.setUsername("hoangpv");
        userEntity.setRoleEntity(roleEntity);
        userRepository.save(userEntity);
      }
    };
  }

}
