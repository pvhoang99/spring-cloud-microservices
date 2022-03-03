package com.example.cqrseslearning.account.events;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class BankTransferCompletedEvent {

  private String bankTransferId;
}
