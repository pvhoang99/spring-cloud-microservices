package com.example.cqrseslearning.account.query;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccountBankEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String axonBankAccountId;
  private long balance;
  private long overdraftLimit;

  public AccountBankEntity(String axonBankAccountId, long balance, long overdraftLimit) {
    this.axonBankAccountId = axonBankAccountId;
    this.balance = balance;
    this.overdraftLimit = overdraftLimit;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAxonBankAccountId() {
    return axonBankAccountId;
  }

  public void setAxonBankAccountId(String axonBankAccountId) {
    this.axonBankAccountId = axonBankAccountId;
  }

  public long getBalance() {
    return balance;
  }

  public void setBalance(long balance) {
    this.balance = balance;
  }

  public long getOverdraftLimit() {
    return overdraftLimit;
  }

  public void setOverdraftLimit(long overdraftLimit) {
    this.overdraftLimit = overdraftLimit;
  }
}
