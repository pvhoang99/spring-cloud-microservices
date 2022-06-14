package com.example.catalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan(value = "com.example.catalog")
@ComponentScan(basePackages = {"com.example.catalog", "com.example.grpc.catalog"})
public class CatalogApplication {

  public static void main(String[] args) {
    SpringApplication.run(CatalogApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(DatabaseInitializer initializer) {
    return args -> initializer.populate();
  }

}
