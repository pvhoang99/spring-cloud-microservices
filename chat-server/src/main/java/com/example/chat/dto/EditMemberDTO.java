package com.example.chat.dto;

import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EditMemberDTO {

  private Set<Long> userIds;
  @NotNull
  private Long roomId;
}
