package com.example.cqrseslearning.account.events;

import lombok.Getter;

@Getter
public class DestinationBankAccountCreditedEvent extends MoneyAddedEvent {

  private String bankTransferId;

  public DestinationBankAccountCreditedEvent(String id, long amount, String bankTransferId) {
    super(id, amount);

    this.bankTransferId = bankTransferId;
  }
}