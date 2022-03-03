package com.example.cqrseslearning.account.aggregate;

import com.example.cqrseslearning.account.command.CreateAccountCommand;
import com.example.cqrseslearning.account.command.EditMoneyCommand;
import com.example.cqrseslearning.account.events.AccountCreatedEvent;
import com.example.cqrseslearning.account.events.EditCommandEvent;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@ToString
public class AccountBank {

  @AggregateIdentifier
  private String id;
  private String username;
  private long overdraftLimit;
  private long balanceInCents;

  @SuppressWarnings("unused")
  private AccountBank() {
  }

  @CommandHandler
  public AccountBank(CreateAccountCommand createAccountCommand) {
    AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getBankAccountId(),
        createAccountCommand.getOverdraftLimit(), createAccountCommand.getUsername()));
  }

  @EventSourcingHandler
  public void on(AccountCreatedEvent event) {
    this.id = event.getId();
    this.overdraftLimit = event.getOverdraftLimit();
    this.balanceInCents = 0;
    this.username = event.getUsername();
  }

  @CommandHandler
  public void onEditMoney(EditMoneyCommand editMoneyCommand) {

    EditCommandEvent editCommandEvent = new EditCommandEvent();
    BeanUtils.copyProperties(editMoneyCommand, editCommandEvent);
    AggregateLifecycle.apply(editCommandEvent);

  }

  @EventSourcingHandler
  public void on(EditCommandEvent editCommandEvent) {
    this.balanceInCents = this.balanceInCents + editCommandEvent.getMoney();
  }
}
