package com.amran.patient.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableOAuth2Client
@EnableEurekaClient
public class PatientManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientManagementServiceApplication.class, args);
    }

    @Bean
    @ConfigurationProperties("security.oauth2.client")
    public ClientCredentialsResourceDetails customOauth2RemoteResource() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        return details;
    }

    @Bean
    @LoadBalanced
    public OAuth2RestTemplate customOauth2RestTemplate() {
        OAuth2RestTemplate template = new OAuth2RestTemplate(customOauth2RemoteResource(),
            new DefaultOAuth2ClientContext());
        return template;
    }
}
