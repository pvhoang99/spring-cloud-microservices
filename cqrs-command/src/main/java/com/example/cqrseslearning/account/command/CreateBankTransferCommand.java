package com.example.cqrseslearning.account.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBankTransferCommand {


  private String bankTransferId;
  private String sourceBankAccountId;
  private String destinationBankAccountId;
  private long amount;

}
