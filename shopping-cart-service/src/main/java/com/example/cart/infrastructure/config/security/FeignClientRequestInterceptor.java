package com.example.cart.infrastructure.config.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class FeignClientRequestInterceptor implements RequestInterceptor {

    private final HttpServletRequest httpServletRequest;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = this.getToken();
        requestTemplate.header(AUTHORIZATION, token);
    }

    private String getToken() {
        return this.httpServletRequest.getHeader("Authorization");
    }
}
