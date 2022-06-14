package com.example.patient.config.security.interceptor;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall;
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

@GrpcGlobalClientInterceptor
@Slf4j
@RequiredArgsConstructor
public class Oauth2ClientInterceptor implements ClientInterceptor {

  private final HttpServletRequest request;
  private final OAuth2ClientContext oAuth2ClientContext;

  static final Metadata.Key<String> CUSTOM_HEADER_KEY =
      Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

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
          token = oAuth2ClientContext.getAccessToken().getValue();
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
}
