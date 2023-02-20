package com.example.catalog.infrastructure.listener;

import com.example.catalog.application.client.InventoryFeignClient;
import com.example.catalog.application.client.dto.ProductDTO;
import com.example.catalog.application.command.product.SyncProductCommand;
import com.example.catalog.infrastructure.broker.Processor;
import com.example.catalog.infrastructure.listener.event.ProductCreatedEvent;
import com.example.common.command.CommandBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CloudStreamListener {

    private final CommandBus commandBus;
    private final InventoryFeignClient inventoryFeignClient;

    @StreamListener(value = Processor.PRODUCT_CREATED)
    public void productCreatedListener(ProductCreatedEvent event) {
        ProductDTO productDTO = this.inventoryFeignClient.getOne(event.getCode());
        SyncProductCommand syncProductCommand = SyncProductCommand.of(
            productDTO.getCode(),
            productDTO.getName(),
            productDTO.getCatalog()
        );
        this.commandBus.execute(syncProductCommand);
    }

}
