package com.example.auth.configSecurity.security;

import java.security.KeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private static String REALM = "KE_REALM";

  private String GRANT_TYPE_PASSWORD = "password";
  private String AUTHORIZATION_CODE = "authorization_code";
  private String REFRESH_TOKEN = "refresh_token";
  private String SCOPE_READ = "read";
  private String SCOPE_WRITE = "write";
  private int VALID_FOREVER = -1;

  @Autowired
  @Qualifier("customClientDetailService")
  private ClientDetailsService clientDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Autowired
  @Qualifier("customUserDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
        .realm(REALM)
        .checkTokenAccess("isAuthenticated()")
        .tokenKeyAccess("permitAll()")
        .allowFormAuthenticationForClients()
        .authenticationEntryPoint(authenticationEntryPoint)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .withClientDetails(clientDetailsService);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .authenticationManager(authenticationManager)
        .tokenStore(tokenStore())
        .tokenEnhancer(tokenEnhancer())
        .userDetailsService(userDetailsService)
    ;
  }


  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(tokenEnhancer());
  }

  @Bean
  public JwtAccessTokenConverter tokenEnhancer() {
    JwtAccessTokenConverter converter = new CustomAccessTokenConverter();
    converter.setKeyPair(keyPair());
    return converter;
  }

  @Autowired
  private KeyUtil keyUtil;

  public KeyPair keyPair() {
    return new KeyPair(keyUtil.getPublicKey(), keyUtil.getPrivateKey());
  }

}
