package com.example.auth.configSecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private static final String RESOURCE_ID = "hoang";

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.anonymous().disable()
        .requestMatchers().antMatchers("/**")
        .and()
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers(
            "/",
            "/js/**",
            "/css/**",
            "/img/**",
            "/webjars/**").permitAll()
        .antMatchers("/**")
        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_PATIENT') or #oauth2.hasScope('trust')")
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
      ;
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(RESOURCE_ID)
    ;
  }
}
