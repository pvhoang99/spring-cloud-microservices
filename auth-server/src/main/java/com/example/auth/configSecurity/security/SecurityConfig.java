package com.example.auth.configSecurity.security;

import java.util.Arrays;
import java.util.Collections;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Autowired
  @Qualifier("customUserDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  private AccessDeniedHandle accessDeniedHandle;

  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.anonymous().and().cors().and().csrf().disable()
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authorizeRequests()
//          .antMatchers(
//            "/",
//            "/js/**",
//            "/css/**",
//            "/img/**",
//            "/webjars/**").permitAll()
//          .antMatchers("/actuator/**").permitAll()
//          .antMatchers("/routes/**").permitAll()
//          .antMatchers("/oauth/token").permitAll()
//          .antMatchers("/oauth/authorize").permitAll()
//          .antMatchers("/signup").permitAll()
//          .anyRequest().authenticated()
//        .and()
//        .formLogin()
//          .loginPage("/login")
//          .permitAll()
//        .and()
//          .logout()
//          .invalidateHttpSession(true)
//          .clearAuthentication(true)
//          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//          .logoutSuccessUrl("/login?logout")
//          .permitAll()
//        .and()
//        .addFilter(new BasicAuthenticationFilter(authenticationManagerBean()))
//        .exceptionHandling()
//        .accessDeniedHandler(accessDeniedHandle)
//        .authenticationEntryPoint(authenticationEntryPoint)
//        .and().httpBasic();
    http.anonymous().and().cors().and().csrf().disable().authorizeRequests()
          .antMatchers("/oauth/check_token").permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }
}
