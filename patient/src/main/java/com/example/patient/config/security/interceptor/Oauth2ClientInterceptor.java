package com.example.patient.config.security.interceptor;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall;
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@Slf4j
@RequiredArgsConstructor
@GrpcGlobalClientInterceptor
public class Oauth2ClientInterceptor implements ClientInterceptor {

  private final HttpServletRequest request;
  private final OAuth2ClientContext oAuth2ClientContext;
  private final ClientCredentialsResourceDetails resource;
  private final AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(
      Arrays.asList(new AuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(),
          new ResourceOwnerPasswordAccessTokenProvider(),
          new ClientCredentialsAccessTokenProvider()));

  static final Metadata.Key<String> CUSTOM_HEADER_KEY = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

  @Override
  public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
      MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {

    return new SimpleForwardingClientCall<ReqT, RespT>(
        channel.newCall(methodDescriptor, callOptions)) {

      @Override
      public void start(Listener<RespT> responseListener, Metadata headers) {
        /* put custom header */
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
          token = getOAuth2AccessToken().getValue();
        }
        headers.put(CUSTOM_HEADER_KEY, token);
        super.start(new SimpleForwardingClientCallListener<RespT>(responseListener) {
          @Override
          public void onHeaders(Metadata headers) {
            super.onHeaders(headers);
          }
        }, headers);
      }
    };
  }

  private OAuth2AccessToken getOAuth2AccessToken() {
    AccessTokenRequest tokenRequest = this.oAuth2ClientContext.getAccessTokenRequest();
    if (tokenRequest == null) {
      throw new AccessTokenRequiredException(
          "Cannot find valid context on request for resource '" + this.resource.getId() + "'.",
          this.resource);
    } else {
      String stateKey = tokenRequest.getStateKey();
      if (stateKey != null) {
        tokenRequest.setPreservedState(this.oAuth2ClientContext.removePreservedState(stateKey));
      }

      OAuth2AccessToken existingToken = this.oAuth2ClientContext.getAccessToken();
      if (existingToken != null) {
        this.oAuth2ClientContext.setAccessToken(existingToken);
      }

      OAuth2AccessToken obtainableAccessToken = this.accessTokenProvider.obtainAccessToken(this.resource, tokenRequest);
      if (obtainableAccessToken != null && obtainableAccessToken.getValue() != null) {
        this.oAuth2ClientContext.setAccessToken(obtainableAccessToken);
        return obtainableAccessToken;
      } else {
        throw new IllegalStateException(" Access token provider returned a null token, which is illegal according to the contract.");
      }
    }
  }
}
