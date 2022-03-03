package com.example.cqrseslearning.account.saga;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;
import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

import com.example.cqrseslearning.account.aggregate.AccountBank;
import com.example.cqrseslearning.account.command.CreditDestinationBankAccountCommand;
import com.example.cqrseslearning.account.command.DebitSourceBankAccountCommand;
import com.example.cqrseslearning.account.command.MarkBankTransferCompletedCommand;
import com.example.cqrseslearning.account.events.BankTransferCreatedEvent;
import com.example.cqrseslearning.account.events.DestinationBankAccountCreditedEvent;
import com.example.cqrseslearning.account.events.SourceBankAccountDebitedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class BankTransferManagementSaga {

  private transient CommandBus commandBus;

  @Autowired
  public void setCommandBus(CommandBus commandBus) {
    this.commandBus = commandBus;
  }

  private String sourceBankAccountId;
  private String destinationBankAccountId;
  private long amount;


  @StartSaga
  @SagaEventHandler(associationProperty = "bankTransferId")
  public void on(BankTransferCreatedEvent event) {

    log.info("saga listen BankTransferCreatedEvent and publish DebitSourceBankAccountCommand");

    this.sourceBankAccountId = event.getSourceBankAccountId();
    this.destinationBankAccountId = event.getDestinationBankAccountId();
    this.amount = event.getAmount();

    DebitSourceBankAccountCommand command = new DebitSourceBankAccountCommand(
        event.getSourceBankAccountId(),
        event.getBankTransferId(),
        event.getAmount());
    commandBus.dispatch(asCommandMessage(command), LoggingCallback.INSTANCE);
  }

  @SagaEventHandler(associationProperty = "bankTransferId")
  public void on(SourceBankAccountDebitedEvent event) {

    log.info("on SourceBankAccountDebitedEvent");

    CreditDestinationBankAccountCommand command = new CreditDestinationBankAccountCommand(destinationBankAccountId,
        event.getBankTransferId(),
        event.getAmount());
    commandBus.dispatch(asCommandMessage(command), LoggingCallback.INSTANCE);
  }

  @EndSaga
  @SagaEventHandler(associationProperty = "bankTransferId")
  public void on(DestinationBankAccountCreditedEvent event) {
    log.info("on DestinationBankAccountCreditedEvent");
    MarkBankTransferCompletedCommand command = new MarkBankTransferCompletedCommand(event.getBankTransferId());
    commandBus.dispatch(asCommandMessage(command), LoggingCallback.INSTANCE);
  }

}
