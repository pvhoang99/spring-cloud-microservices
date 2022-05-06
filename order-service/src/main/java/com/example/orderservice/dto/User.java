package com.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private Long userId;
  private String username;
  private String fullName;
  private String email;
}

