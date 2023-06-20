package com.example.auth.domain.user;

import com.example.common.domain.ValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Email implements ValueObject {

  private final String email;

  private Email(String email) {
    this.email = email;
    validate(email);
  }

  public static Email create(String email) {
    return new Email(email);
  }

  private static void validate(String email) {

  }

  public String getValue() {
    return email;
  }

}
