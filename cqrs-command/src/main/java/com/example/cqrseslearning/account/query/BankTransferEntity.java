package com.example.cqrseslearning.account.query;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bank_transfer")
@NoArgsConstructor
public class BankTransferEntity {

  @Id
  @GeneratedValue
  private long id;
  private String axonBankTransferId;
  private String sourceBankAccountId;
  private String destinationBankAccountId;
  private long amount;
  private Status status;

  public BankTransferEntity(String axonBankTransferId, String sourceBankAccountId,
      String destinationBankAccountId, long amount,
      Status status) {
    this.axonBankTransferId = axonBankTransferId;
    this.sourceBankAccountId = sourceBankAccountId;
    this.destinationBankAccountId = destinationBankAccountId;
    this.amount = amount;
    this.status = status;
  }


  public static enum Status {
    STARTED,
    FAILED,
    COMPLETED
  }

}
