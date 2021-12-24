package com.example.cqrseslearning.account.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditCommandEvent {

  private String bankAccountId;

  private long money;

}
