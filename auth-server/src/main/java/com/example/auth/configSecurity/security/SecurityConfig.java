package com.example.auth.configSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
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
    http.anonymous().and().cors().and().csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
          .antMatchers(
            "/",
            "/js/**",
            "/css/**",
            "/img/**",
            "/webjars/**").permitAll()
          .antMatchers("/actuator/**").permitAll()
          .antMatchers("/routes/**").permitAll()
          .antMatchers("/oauth/token").permitAll()
          .antMatchers("/oauth/authorize").permitAll()
          .antMatchers("/signup").permitAll()
          .anyRequest().authenticated()
        .and()
        .formLogin()
          .loginPage("/login")
          .permitAll()
        .and()
          .logout()
          .invalidateHttpSession(true)
          .clearAuthentication(true)
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/login?logout")
          .permitAll()
        .and()
        .addFilter(new BasicAuthenticationFilter(authenticationManagerBean()))
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandle)
        .authenticationEntryPoint(authenticationEntryPoint)
    ;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }
}
