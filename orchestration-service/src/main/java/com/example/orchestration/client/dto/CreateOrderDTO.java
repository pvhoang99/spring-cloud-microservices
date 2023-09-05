package com.example.orchestration.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class CreateOrderDTO {
    private String transactionId;
}
