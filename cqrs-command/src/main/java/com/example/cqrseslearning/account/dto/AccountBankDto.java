package com.example.cqrseslearning.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountBankDto {

  private String id;
  private long overdraftLimit;
  private long money;
}