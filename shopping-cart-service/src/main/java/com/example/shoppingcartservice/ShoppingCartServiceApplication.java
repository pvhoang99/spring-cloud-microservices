package com.example.shoppingcartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringCloudApplication
@EnableOAuth2Sso
@EnableFeignClients
@EnableJpaAuditing
@EnableJpaRepositories
public class ShoppingCartServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShoppingCartServiceApplication.class, args);
  }

}
