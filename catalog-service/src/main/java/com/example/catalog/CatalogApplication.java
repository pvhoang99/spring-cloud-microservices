package com.example.catalog;

import com.example.catalog.dto.Catalog;
import com.example.catalog.dao.entity.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringCloudApplication
@EnableNeo4jRepositories
@EnableConfigurationProperties
@ConfigurationPropertiesScan(value = "com.example.catalog")
public class CatalogApplication {

  public static void main(String[] args) {
    SpringApplication.run(CatalogApplication.class, args);
  }

//  @Bean
//  public CommandLineRunner commandLineRunner(DatabaseInitializer databaseInitializer) {
//    return args -> databaseInitializer.populate();
//  }

  @Configuration
  public static class CustomizedRestMvcConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
      config.setBasePath("/api/v1");
      config.exposeIdsFor(Catalog.class, Product.class);
    }

  }

}
