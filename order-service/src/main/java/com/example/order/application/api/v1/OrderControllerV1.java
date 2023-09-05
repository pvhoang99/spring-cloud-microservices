package com.example.order.application.api.v1;

import com.example.common.command.CommandBus;
import com.example.order.application.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/orders")
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final CommandBus commandBus;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody CreateOrderCommand command) {
        return ResponseEntity.ok(this.commandBus.execute(command));
    }
}
