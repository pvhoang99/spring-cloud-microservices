package com.amran.clinic.management;

import com.amran.clinic.management.security.ConfigOAuth2;
import com.amran.clinic.management.security.Oauth2Config;
import java.util.ArrayList;
import java.util.List;
import lombok.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableOAuth2Client
public class ClinicManagementServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClinicManagementServiceApplication.class, args);
  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @LoadBalanced
  @Bean
  public OAuth2RestTemplate loadBalancedOauth2RestTemplate(
      OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
    return new OAuth2RestTemplate(resource, context);
  }
//  @Bean
//  @LoadBalanced
//  public OAuth2RestTemplate oauth2RestTemplate1() {
//    return new OAuth2RestTemplate(new ConfigOAuth2());
//  }

  @Bean
//  @ConfigurationProperties("security.oauth2.client")
  public OAuth2ProtectedResourceDetails customOauth2RemoteResource() {
    OAuth2ProtectedResourceDetails details = new Oauth2Config();
    return details;
  }
//
//  @Bean
//  @LoadBalanced
//  public OAuth2RestTemplate customOauth2RestTemplate() {
//    OAuth2RestTemplate template = new OAuth2RestTemplate(customOauth2RemoteResource(),
//        new DefaultOAuth2ClientContext());
//    return template;
//  }

}
