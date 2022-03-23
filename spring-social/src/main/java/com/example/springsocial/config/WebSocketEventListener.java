package com.example.springsocial.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class WebSocketEventListener implements ApplicationListener<SessionConnectedEvent> {

  private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

  @EventListener
  public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    logger.info("Received a new web socket connection");

  }

  @Override
  public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
    logger.debug("Client connected.");

  }
}
