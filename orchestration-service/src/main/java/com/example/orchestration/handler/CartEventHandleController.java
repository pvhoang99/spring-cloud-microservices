package com.example.orchestration.handler;

import com.example.orchestration.dto.CartConfirmedEvent;
import com.example.orchestration.service.CartEventHandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cart-event")
@RequiredArgsConstructor
public class CartEventHandleController {

    private final CartEventHandleService cartEventHandleService;

    @PostMapping("/confirmed")
    public ResponseEntity<Void> confirmedHandle(@RequestBody CartConfirmedEvent cartConfirmedEvent) {
        this.cartEventHandleService.cartConfirmedHandle(cartConfirmedEvent);

        return ResponseEntity.ok().build();
    }


}
