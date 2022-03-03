package com.example.cqrseslearning.account.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SourceBankAccountNotFoundEvent {

  private String bankTransferId;

}
