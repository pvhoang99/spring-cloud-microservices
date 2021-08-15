package com.amran.patient.management.security;

import com.amran.patient.management.security.JwtConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author : Amran Hosssain on 6/27/2020
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().disable()
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/eureka/**", "/login").permitAll()
            .anyRequest()
            .authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("ehealth")
//            .tokenServices(tokenServices())
        ;
    }

//    @Value("${client_id}")
//    private String clientId;
//
//    @Value("${client_credential}")
//    private String clientSecret;
//
//    @Value("${check_authorization_url}")
//    private String checkAuthUrl;
//
//    @Bean
//    public ResourceServerTokenServices tokenServices() {
//        RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setClientId(clientId);
//        tokenServices.setClientSecret(clientSecret);
//        tokenServices.setCheckTokenEndpointUrl(checkAuthUrl);
//        return tokenServices;
//    }


}
