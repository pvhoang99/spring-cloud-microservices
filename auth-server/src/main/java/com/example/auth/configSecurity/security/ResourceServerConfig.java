package com.example.auth.configSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private static final String RESOURCE_ID = "hoang";

  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;

  @Override
  public void configure(HttpSecurity http) throws Exception {
//    http.anonymous().disable()
//        .requestMatchers().antMatchers("/**")
//        .and()
//        .authorizeRequests()
//        .antMatchers("/oauth/revoke_token").permitAll()
//        .antMatchers("/oauth/authorize").permitAll()
//        .anyRequest().permitAll()
//        .antMatchers(
//            "/",
//            "/js/**",
//            "/css/**",
//            "/img/**",
//            "/webjars/**").permitAll()
//        .antMatchers("/**")
//        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_PATIENT') or #oauth2.hasScope('trust')")
//        .and().formLogin()
//        .loginPage("/login")
//        .successHandler(authenticationSuccessHandler)
//        .permitAll()
//        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
      ;
    http.csrf().disable()
        .antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/oauth/revoke_token").permitAll()
        .antMatchers("/oauth/authorize").permitAll()
        .anyRequest().permitAll()
        .and().formLogin()
        .loginPage("/login")
        .successHandler(authenticationSuccessHandler)
        .permitAll()
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
        .and().httpBasic();
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(RESOURCE_ID)
    ;
  }

  @Bean
  HttpSessionSecurityContextRepository contextRepository() {
    return new HttpSessionSecurityContextRepository();
  }
}
