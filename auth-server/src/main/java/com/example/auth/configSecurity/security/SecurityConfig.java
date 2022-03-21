package com.example.auth.configSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
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
  private PasswordEncoder passwordEncoder;

  //  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//        .and()
//        .authorizeRequests()
//        .antMatchers("/resources/**").permitAll()
//        .antMatchers("/oauth/token", "/login", "/logout", "/api/user").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .formLogin()
//        .loginPage("/login")
//        .failureUrl("/login?error=true")
//        .permitAll()
//        .and()
//        .logout()
//        .invalidateHttpSession(true)
//        .clearAuthentication(true)
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        .logoutSuccessHandler(new RedirectLogoutSuccessHandler())
//        .deleteCookies("auth_code", "JSESSIONID")
//        .permitAll()
//        .and().httpBasic();
//    http.anonymous().and().cors().and().csrf().disable();
//  }
//
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/login**", "/oauth/token")
        .permitAll()
        .and()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

}
