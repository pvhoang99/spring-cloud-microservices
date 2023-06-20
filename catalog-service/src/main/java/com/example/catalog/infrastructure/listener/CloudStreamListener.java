package com.example.catalog.infrastructure.listener;

import com.example.common.command.CommandBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CloudStreamListener {

  private final CommandBus commandBus;
//    private final InventoryFeignClient inventoryFeignClient;


}
