/*
 * Copyright (c) 2016. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cqrseslearning.account.aggregate;


import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.example.cqrseslearning.account.command.CreateBankTransferCommand;
import com.example.cqrseslearning.account.command.MarkBankTransferCompletedCommand;
import com.example.cqrseslearning.account.events.BankTransferCompletedEvent;
import com.example.cqrseslearning.account.events.BankTransferCreatedEvent;
import com.example.cqrseslearning.account.query.BankTransferEntity.Status;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class BankTransfer {

  @AggregateIdentifier
  private String bankTransferId;
  private String sourceBankAccountId;
  private String destinationBankAccountId;
  private long amount;
  private Status status;

  @SuppressWarnings("unused")
  protected BankTransfer() {
  }

  @CommandHandler
  public BankTransfer(CreateBankTransferCommand createBankTransferCommand) {

    log.info("create transfer");

    apply(new BankTransferCreatedEvent(createBankTransferCommand.getBankTransferId(),
        createBankTransferCommand.getSourceBankAccountId(),
        createBankTransferCommand.getDestinationBankAccountId(),
        createBankTransferCommand.getAmount()));
  }

  @EventHandler
  public void on(BankTransferCreatedEvent event) throws Exception {
    log.info("event handler add properties to aggregate");

    this.bankTransferId = event.getBankTransferId();
    this.sourceBankAccountId = event.getSourceBankAccountId();
    this.destinationBankAccountId = event.getDestinationBankAccountId();
    this.amount = event.getAmount();
    this.status = Status.STARTED;
  }

  @CommandHandler
  public void handle(MarkBankTransferCompletedCommand command) {
    apply(new BankTransferCompletedEvent(command.getBankTransferId()));
  }

  @EventHandler
  public void on(BankTransferCompletedEvent event) {
    this.status = Status.COMPLETED;
  }

}   