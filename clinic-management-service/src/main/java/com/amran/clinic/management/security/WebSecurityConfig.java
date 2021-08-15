//package com.amran.clinic.management.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
///**
// * @Author : Amran Hosssain on 6/27/2020
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Value("${client_id}")
//  private String clientId;
//
//  @Value("${client_credential}")
//  private String clientSecret;
//
//  @Value("${check_authorization_url}")
//  private String checkAuthUrl;
//
//  @Bean
//  public ResourceServerTokenServices tokenServices() {
//    RemoteTokenServices tokenServices = new RemoteTokenServices();
//    tokenServices.setClientId(clientId);
//    tokenServices.setClientSecret(clientSecret);
//    tokenServices.setCheckTokenEndpointUrl(checkAuthUrl);
//    return tokenServices;
////    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
////    defaultTokenServices.setTokenStore(tokenStore());
////    defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter());
////    return defaultTokenServices;
//  }
//
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
//    authenticationManager.setTokenServices(tokenServices());
//    return authenticationManager;
//  }
//
//  @Bean
//  public TokenStore tokenStore() {
//    return new JwtTokenStore(jwtAccessTokenConverter());
//  }
//
//  @Bean
//  public JwtAccessTokenConverter jwtAccessTokenConverter() {
//    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//    converter.setVerifierKey("private");
//    return converter;
//  }
//}
