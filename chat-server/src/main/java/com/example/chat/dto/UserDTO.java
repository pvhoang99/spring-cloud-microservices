package com.example.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  private Long userId;
  private String username;
  private String fullName;
  private String email;
}
