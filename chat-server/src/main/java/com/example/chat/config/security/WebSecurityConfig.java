package com.example.chat.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/ws");
  }
}
