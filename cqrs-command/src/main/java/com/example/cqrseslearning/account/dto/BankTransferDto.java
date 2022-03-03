package com.example.cqrseslearning.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
@NoArgsConstructor
public class BankTransferDto {

  private String sourceBankAccountId;
  private String destinationBankAccountId;
  private long amount;
}
