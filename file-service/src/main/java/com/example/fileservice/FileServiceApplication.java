package com.example.fileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableOAuth2Sso
@EnableFeignClients
public class FileServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(FileServiceApplication.class, args);
  }

}
