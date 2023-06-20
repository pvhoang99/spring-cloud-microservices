package com.example.inventory.infrastructure.listener;

import com.example.common.command.CommandBus;
import com.example.inventory.infrastructure.broker.Processor;
import com.example.inventory.infrastructure.listener.event.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CloudStreamListener {

  private final CommandBus commandBus;

  @StreamListener(value = Processor.PRODUCT_CREATED)
  public void productCreatedListener(ProductCreatedEvent event) {
    log.info("event {}", event);
  }

}
