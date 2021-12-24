package com.example.cqrsquery;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface AccountSink {

  String INPUT = "user";

  @Input(AccountSink.INPUT)
  SubscribableChannel user();

}
