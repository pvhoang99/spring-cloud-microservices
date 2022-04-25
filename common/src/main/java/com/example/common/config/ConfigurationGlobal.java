package com.example.common.config;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

public class ConfigurationGlobal {

  @Bean
  public Gson gson() {
    return new Gson();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();

    configuration
        .setAllowedOrigins(ImmutableList.of("*"));
    configuration.setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    configuration
        .setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

}
