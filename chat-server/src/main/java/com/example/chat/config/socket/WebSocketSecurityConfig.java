package com.example.chat.config.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@EnableGlobalMethodSecurity
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

  @Override
  protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
    messages
        .simpTypeMatchers(SimpMessageType.CONNECT, SimpMessageType.DISCONNECT,
            SimpMessageType.UNSUBSCRIBE).permitAll()
        .simpDestMatchers("/user/queue/reply").hasRole("USER")
        .simpMessageDestMatchers("/app/**").hasRole("USER")
        .anyMessage().authenticated();
  }

  @Override
  protected boolean sameOriginDisabled() {
    return true;
  }
}
