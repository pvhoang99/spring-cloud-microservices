package com.example.chat.config.audit;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.neo4j.annotation.EnableNeo4jAuditing;

@Configuration
@EnableNeo4jAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "dateTimeProvider")
public class AuditingConfig {

  @Bean(name = "auditorProvider")
  public AuditorAware<String> auditorProvider() {
    return new SpringSecurityAuditorAware();
  }


  @Bean(name = "dateTimeProvider")
  public DateTimeProvider dateTimeProvider() {
    return () -> Optional.of(LocalDateTime.now());
  }
}
