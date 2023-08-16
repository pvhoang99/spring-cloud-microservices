package com.example.common.config;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableAspectJAutoProxy
public class CorsConfiguration {

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    @Primary
    public CorsConfigurationSource corsConfigurationSource() {
        final org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();

        configuration.setAllowedMethods(ImmutableList.of("*"));
        configuration.setAllowCredentials(false);
        configuration.setAllowedHeaders(ImmutableList.of("*"));
        configuration.setAllowedOriginPatterns(ImmutableList.of("*"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
