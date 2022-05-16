package com.example.auth.config.security;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }

  @Setter(onMethod = @__({@Autowired}))
  @Qualifier("customUserDetailsService")
  private UserDetailsService userDetailsService;

  @Setter(onMethod = @__({@Autowired}))
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/login**", "/oauth/token", "/logout**", "/")
        .permitAll()
        .and()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll()
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessHandler(redirectLogoutSuccessHandler())
        .deleteCookies("auth_code", "JSESSIONID")
        .permitAll();
  }

  @Bean
  public RedirectLogoutSuccessHandler redirectLogoutSuccessHandler() {
    return new RedirectLogoutSuccessHandler();
  }

}
