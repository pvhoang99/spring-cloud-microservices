package com.example.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginByCodeDTO {

  public String code;
  public String state;
  public String redirectURI;
}
