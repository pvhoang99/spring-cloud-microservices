package com.example.cqrseslearning.projector;

import com.example.cqrseslearning.account.aggregate.AccountBank;
import com.example.cqrseslearning.account.events.AccountCreatedEvent;
import com.example.cqrseslearning.account.events.EditCommandEvent;
import com.example.cqrseslearning.account.events.MoneyAddedEvent;
import com.example.cqrseslearning.account.events.MoneySubtractedEvent;
import com.example.cqrseslearning.account.query.AccountBankEntity;
import com.example.cqrseslearning.account.query.AccountBankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class AccountBankEventListener {

  private final AccountBankRepository repository;

  @Qualifier("accountAggregateEventSourcingRepository")
  private final EventSourcingRepository<AccountBank> accountAggregateEventSourcingRepository;

  @EventHandler
  public void on(AccountCreatedEvent event) {

    repository.save(new AccountBankEntity(event.getId(), 0, event.getOverdraftLimit()));
  }

  @EventHandler
  public void on(EditCommandEvent editCommandEvent) {
    log.info(accountAggregateEventSourcingRepository.load(editCommandEvent.getBankAccountId())
        .getWrappedAggregate().getAggregateRoot().toString());
    AccountBank accountBank = accountAggregateEventSourcingRepository
        .load(editCommandEvent.getBankAccountId()).getWrappedAggregate().getAggregateRoot();

    AccountBankEntity accountBankEntity = repository
        .findByAxonBankAccountId(editCommandEvent.getBankAccountId());
    accountBankEntity.setBalance(editCommandEvent.getMoney());
    repository.save(accountBankEntity);

  }

  @EventHandler
  public void on(MoneyAddedEvent event) {
    log.info("add money from account destination");

    AccountBankEntity bankAccountEntry = repository
        .findByAxonBankAccountId(event.getBankAccountId());
    bankAccountEntry.setBalance(bankAccountEntry.getBalance() + event.getAmount());
    repository.save(bankAccountEntry);

  }

  @EventHandler
  public void on(MoneySubtractedEvent event) {
    log.info("subtract money from account root");

    AccountBankEntity bankAccountEntry = repository
        .findByAxonBankAccountId(event.getBankAccountId());
    bankAccountEntry.setBalance(bankAccountEntry.getBalance() - event.getAmount());

    repository.save(bankAccountEntry);

  }

}
