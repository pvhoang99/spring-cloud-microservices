package com.example.cqrseslearning.account.events;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public abstract class MoneyAddedEvent {

  private String bankAccountId;
  private long amount;

}
