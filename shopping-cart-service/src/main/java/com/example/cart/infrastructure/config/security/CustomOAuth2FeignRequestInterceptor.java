package com.example.cart.infrastructure.config.security;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class CustomOAuth2FeignRequestInterceptor extends OAuth2FeignRequestInterceptor {

  @Setter(onMethod = @__({@Autowired}))
  private HttpServletRequest httpServletRequest;

  public CustomOAuth2FeignRequestInterceptor(
      OAuth2ClientContext oAuth2ClientContext,
      OAuth2ProtectedResourceDetails resource) {
    super(oAuth2ClientContext, resource);
  }

  public CustomOAuth2FeignRequestInterceptor(
      OAuth2ClientContext oAuth2ClientContext,
      OAuth2ProtectedResourceDetails resource, String tokenType, String header) {
    super(oAuth2ClientContext, resource, tokenType, header);
  }

  @Override
  protected String extract(String tokenType) {
    if (Objects.nonNull(httpServletRequest)) {
      String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
      if (StringUtils.isNotEmpty(token)) {
        return token;
      } else {
        return super.extract(tokenType);
      }
    }
    return super.extract(tokenType);
  }

  @Override
  public OAuth2AccessToken getToken() {
    return super.acquireAccessToken();
  }
}
