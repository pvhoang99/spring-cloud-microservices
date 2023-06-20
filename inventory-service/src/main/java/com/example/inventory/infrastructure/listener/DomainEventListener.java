package com.example.inventory.infrastructure.listener;

import com.example.common.event.DomainEvent;
import com.example.inventory.infrastructure.broker.BrokerChannelFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Slf4j
@RequiredArgsConstructor
public class DomainEventListener {

  private final BrokerChannelFactory brokerChannelFactory;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(DomainEvent event) {
    log.info("DomainEventListener preparing to send: {} ", event);
    Message<DomainEvent> message = this.message(event);
    MessageChannel messageChannel = this.brokerChannelFactory.of(event);
    if (messageChannel != null) {
      boolean success = messageChannel.send(message);
      log.info("DomainEventListener sent: {}, success: {}", event, success);

      return;
    }
    log.warn("DomainEventListener messageChannel is null");
  }

  private <T> Message<T> message(T val) {
    return MessageBuilder.withPayload(val).build();
  }

}
