package com.example.inventory.infrastructure.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Processor {

    String PRODUCT_CREATED = "out-inventory_product_created";

    @Output(value = PRODUCT_CREATED)
    MessageChannel productCreated();

}
