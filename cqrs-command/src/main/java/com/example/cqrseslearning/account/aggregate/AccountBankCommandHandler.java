package com.example.cqrseslearning.account.aggregate;

import org.axonframework.eventhandling.EventBus;
import org.axonframework.modelling.command.Repository;

public class AccountBankCommandHandler {

  private Repository<AccountBank> repository;
  private EventBus eventBus;

  public AccountBankCommandHandler(Repository<AccountBank> repository, EventBus eventBus) {
    this.repository = repository;
    this.eventBus = eventBus;
  }

}
