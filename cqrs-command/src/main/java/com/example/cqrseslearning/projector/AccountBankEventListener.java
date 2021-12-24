package com.example.cqrseslearning.projector;

import com.example.cqrseslearning.account.aggregate.AccountBank;
import com.example.cqrseslearning.account.events.AccountCreatedEvent;
import com.example.cqrseslearning.account.events.EditCommandEvent;
import com.example.cqrseslearning.account.query.AccountBankEntity;
import com.example.cqrseslearning.account.query.AccountBankRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class AccountBankEventListener {

  private final AccountBankRepository repository;
//  private final Source source;

  @Autowired
  @Qualifier("accountAggregateEventSourcingRepository")
  private EventSourcingRepository<AccountBank> accountAggregateEventSourcingRepository;

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
    AccountBankEvent accountBankEvent = new AccountBankEvent();
    accountBankEvent.setSubject(accountBank);

  }


}
