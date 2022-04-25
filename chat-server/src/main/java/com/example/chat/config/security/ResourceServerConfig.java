package com.example.chat.config.security;

import com.example.common.config.ConfigurationGlobal;
import com.google.common.collect.ImmutableList;
import feign.RequestInterceptor;
import javax.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableResourceServer
@AllArgsConstructor
@Import(ConfigurationGlobal.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private final ResourceServerProperties sso;

  private final OAuth2ClientContext oAuth2ClientContext;

  private final CorsConfigurationSource corsConfigurationSource;

  @Bean
  @ConfigurationProperties(prefix = "security.oauth2.client")
  public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
    return new ClientCredentialsResourceDetails();
  }

  @Bean
  public RequestInterceptor oauth2FeignRequestInterceptor() {
    CustomOAuth2FeignRequestInterceptor customOAuth2FeignRequestInterceptor = new CustomOAuth2FeignRequestInterceptor(
        oAuth2ClientContext, clientCredentialsResourceDetails());
    customOAuth2FeignRequestInterceptor.setAccessTokenProvider(accessTokenProvider());
    return customOAuth2FeignRequestInterceptor;
  }

  @Bean
  public AccessTokenProvider accessTokenProvider() {
    return new ClientCredentialsAccessTokenProvider();
  }

  @Bean
  @LoadBalanced
  public OAuth2RestOperations restTemplate() {
    return new OAuth2RestTemplate(clientCredentialsResourceDetails(), oAuth2ClientContext);
  }

  @LoadBalanced
  @Bean("loadBalancedRestTemplate")
  public RestTemplate loadBalancedRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public DefaultOAuth2UserService defaultOAuth2UserService() {
    return new CustomOAuth2UserService();
  }

  @Bean
  @Primary
  public RemoteTokenServices remoteTokenServices() {
    RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
    remoteTokenServices.setClientSecret(clientCredentialsResourceDetails().getClientId());
    remoteTokenServices.setClientSecret(clientCredentialsResourceDetails().getClientSecret());
    remoteTokenServices
        .setCheckTokenEndpointUrl(clientCredentialsResourceDetails().getAccessTokenUri());
    remoteTokenServices.setRestTemplate(loadBalancedRestTemplate());
    return remoteTokenServices;
  }

  @Bean
  public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
    return new HttpCookieOAuth2AuthorizationRequestRepository();
  }

  @Bean
  public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
    return new OAuth2AuthenticationFailureHandler();
  }

  @Bean
  public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
    return new OAuth2AuthenticationSuccessHandler();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/api/v1/login",
            "/api/v1/user/all", "/oauth2/**")
        .permitAll()
        .antMatchers("/ws**", "/ws/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new OAuth2AuthenticationEntryPoint())
        .accessDeniedHandler(new OAuth2AccessDeniedHandler())
        .and()
        .oauth2Login()
        .authorizationEndpoint()
        .baseUri("/oauth2/authorize")
        .authorizationRequestRepository(cookieAuthorizationRequestRepository())
        .and()
        .redirectionEndpoint()
        .baseUri("/oauth2/callback/*")
        .and()
        .userInfoEndpoint()
        .userService(defaultOAuth2UserService())
        .and()
        .successHandler(oAuth2AuthenticationSuccessHandler())
        .failureHandler(oAuth2AuthenticationFailureHandler());
    http.csrf().disable();
    http.cors().configurationSource(corsConfigurationSource);
    http.httpBasic().disable();
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenServices(remoteTokenServices());
    resources.resourceId(sso.getResourceId());
    resources.stateless(true);
  }

}