package com.example.cqrseslearning.projector;

import com.example.cqrseslearning.account.aggregate.BankTransfer;
import com.example.cqrseslearning.account.events.BankTransferCompletedEvent;
import com.example.cqrseslearning.account.events.BankTransferCreatedEvent;
import com.example.cqrseslearning.account.query.BankTransferEntity;
import com.example.cqrseslearning.account.query.BankTransferEntity.Status;
import com.example.cqrseslearning.account.query.BankTransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BankTransferEventListener {

  private final BankTransferRepository repository;

  @Qualifier("bankTransferEventSourcingRepository")
  private final EventSourcingRepository<BankTransfer> accountAggregateEventSourcingRepository;

  @EventHandler
  public void on(BankTransferCreatedEvent event) {
    repository.save(
        new BankTransferEntity(event.getBankTransferId(), event.getSourceBankAccountId(),
            event.getDestinationBankAccountId(), event.getAmount(),
            Status.STARTED));
  }

  @EventHandler
  public void on(BankTransferCompletedEvent event) {
    BankTransferEntity bankTransferEntity = repository.findByAxonBankTransferId(event.getBankTransferId());
    bankTransferEntity.setStatus(Status.COMPLETED);

    repository.save(bankTransferEntity);
  }
}
