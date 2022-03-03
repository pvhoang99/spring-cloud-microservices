package com.example.cqrseslearning.account.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankTransferFailedEvent {

  private String bankTransferId;

}
