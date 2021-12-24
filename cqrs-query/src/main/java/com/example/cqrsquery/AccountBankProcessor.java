package com.example.cqrsquery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@Slf4j
public class AccountBankProcessor {

  @StreamListener(value = AccountSink.INPUT)
  public void apply(Message<Object> message) {
    log.info(message.toString());
  }

}
