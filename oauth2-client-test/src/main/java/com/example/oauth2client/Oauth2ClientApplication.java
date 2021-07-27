package com.example.oauth2client;

import static org.springframework.web.servlet.function.ServerResponse.ok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class Oauth2ClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(Oauth2ClientApplication.class, args);
    System.out.println(new BCryptPasswordEncoder().encode("1"));
  }

  @Bean
  RouterFunction<ServerResponse> routerFunction() {
    return RouterFunctions.route(RequestPredicates.GET("/hi"), r-> ok().body("Hello"));
  }
}
