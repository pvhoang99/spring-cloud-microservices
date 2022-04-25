package com.example.auth.config.security;

import com.example.common.config.ConfigurationGlobal;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableResourceServer
@Import(ConfigurationGlobal.class)
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private final CorsConfigurationSource corsConfigurationSource;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    super.configure(resources);
    resources.resourceId("auth-server");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.cors().configurationSource(corsConfigurationSource);
    http.httpBasic().disable();

    http.requestMatchers()
        .antMatchers("/api/**")
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/user/revoke").permitAll()
        .expressionHandler(webExpressionHandler())
        .anyRequest().authenticated()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new OAuth2AccessDeniedHandler())
        .authenticationEntryPoint(new OAuth2AuthenticationEntryPoint());
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
  }

  @Bean
  public RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    String hierarchy = "ROLE_ADMIN > ROLE_USER";
    roleHierarchy.setHierarchy(hierarchy);
    return roleHierarchy;
  }

  private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
    DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
    defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
    return defaultWebSecurityExpressionHandler;
  }

  @Bean
  public RoleHierarchyVoter roleHierarchyVoter() {
    return new RoleHierarchyVoter(roleHierarchy());
  }

  @Bean
  public HttpSessionSecurityContextRepository contextRepository() {
    return new HttpSessionSecurityContextRepository();
  }
}
