package com.example.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableOAuth2Sso
@EnableFeignClients
@EnableConfigurationProperties
@ConfigurationPropertiesScan(value = "com.example.*")
public class PatientApplication {

  public static void main(String[] args) {
    SpringApplication.run(PatientApplication.class, args);
  }

}
