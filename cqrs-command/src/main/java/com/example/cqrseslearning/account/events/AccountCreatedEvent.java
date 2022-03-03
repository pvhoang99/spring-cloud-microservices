package com.example.cqrseslearning.account.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AccountCreatedEvent {

  private String id;
  private long overdraftLimit;
  private String username;
}
