package com.example.gatewayserver;

import com.google.common.collect.ImmutableList;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringCloudApplication
public class GatewayServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayServerApplication.class, args);
  }

//  @Bean
//  public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
//    return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//        .circuitBreakerConfig(CircuitBreakerConfig.custom()
//            .slidingWindowSize(10)
//            .build())
//        .timeLimiterConfig(
//            TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(5000)).build())
//        .build());
//  }

  @Bean
  public CorsWebFilter corsWebFilter() {

    final CorsConfiguration configuration = new CorsConfiguration();
    configuration
        .setAllowedOrigins(ImmutableList.of("*"));
    configuration.setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    configuration
        .setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return new CorsWebFilter(source);
  }

}
