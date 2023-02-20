package com.example.catalog.infrastructure.broker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Processor {

    String PRODUCT_CREATED = "inventory_product_created";

    @Input(value = PRODUCT_CREATED)
    SubscribableChannel productCreated();

}
