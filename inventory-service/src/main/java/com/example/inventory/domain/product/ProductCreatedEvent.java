package com.example.inventory.domain.product;

import com.example.common.event.DomainEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ProductCreatedEvent implements DomainEvent {

    private String code;

    public static ProductCreatedEvent of(String code) {
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        productCreatedEvent.setCode(code);

        return productCreatedEvent;
    }

}
