package com.example.cart.infrastructure.listener.event;

import com.example.common.command.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainEventListener {

  private final CommandBus commandBus;

}
