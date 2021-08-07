package com.example.auth.configSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .requestMatchers().antMatchers("/**")
        .and()
        .authorizeRequests()
        .antMatchers("/oauth/*", "/login", "/login-error", "/logout").permitAll()
        .and()
        .authorizeRequests()
        .expressionHandler(webExpressionHandler())
        .anyRequest().authenticated()
        .and().formLogin()
        .loginPage("/login")
        .successHandler(authenticationSuccessHandler)
        .failureUrl("/login-error")
        .permitAll()
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
        .authenticationEntryPoint(new OAuth2AuthenticationEntryPoint())
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessHandler(new RedirectLogoutSuccessHandler())
        .deleteCookies("auth_code", "JSESSIONID")
        .permitAll()
        .and().httpBasic();
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

  @Autowired
  private AccessDeniedHandle accessDeniedHandle;

  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint;

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
