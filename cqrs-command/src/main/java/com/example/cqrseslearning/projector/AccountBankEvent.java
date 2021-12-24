package com.example.cqrseslearning.projector;

import com.example.cqrseslearning.account.aggregate.AccountBank;

public class AccountBankEvent extends DomainEvent<AccountBank, String> {

  private AccountBank subject;

  @Override
  public AccountBank getSubject() {
    return subject;
  }

  @Override
  public void setSubject(AccountBank subject) {

    this.subject = subject;

  }
}
