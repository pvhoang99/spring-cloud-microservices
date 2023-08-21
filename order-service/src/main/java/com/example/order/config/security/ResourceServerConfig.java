package com.example.order.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

  @Order(0)
  @Bean
  public SecurityFilterChain restApiSecurityFilterChain(HttpSecurity http) throws Exception {

    http
            .securityMatcher("/**")
            .authorizeHttpRequests((authorize) -> authorize
                    .anyRequest().authenticated()
            )
            .exceptionHandling(
                    handler -> handler
                            .accessDeniedHandler(new AccessDeniedHandlerImpl())
                            .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
            )
            .oauth2ResourceServer((resourceServer) -> resourceServer
                    .jwt(Customizer.withDefaults())
            );

    return http.build();
  }
}
