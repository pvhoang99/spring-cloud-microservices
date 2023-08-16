package com.example.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignClientRequestInterceptor implements RequestInterceptor {

    private final HttpServletRequest httpServletRequest;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = this.getToken();
        requestTemplate.header(OAuth2AccessTokenInterceptor.AUTHORIZATION, token);
    }

    private String getToken() {
        return this.httpServletRequest.getHeader(OAuth2AccessTokenInterceptor.AUTHORIZATION);
    }
}
