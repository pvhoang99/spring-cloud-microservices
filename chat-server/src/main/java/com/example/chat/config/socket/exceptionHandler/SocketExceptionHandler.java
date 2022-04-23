package com.example.chat.config.socket.exceptionHandler;

import org.springframework.messaging.Message;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

public class SocketExceptionHandler extends StompSubProtocolErrorHandler {

  @Override
  public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage,
      Throwable ex) {
    //TODO: can custom lai message
    return super.handleClientMessageProcessingError(clientMessage, ex);
  }
}
