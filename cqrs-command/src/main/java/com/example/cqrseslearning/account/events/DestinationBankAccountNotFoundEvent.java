package com.example.cqrseslearning.account.events;

import lombok.Value;

@Value
public class DestinationBankAccountNotFoundEvent {

  private String bankTransferId;
}