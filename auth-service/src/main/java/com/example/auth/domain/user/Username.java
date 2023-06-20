package com.example.auth.domain.user;

import com.example.common.domain.ValueObject;
import com.example.common.exception.BadRequestException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Username implements ValueObject {

  private static final int MIN_LENGTH = 6;

  private static final int MAX_LENGTH = 12;

  private final String username;

  private Username(String username) {
    this.username = this.normalize(username);
    this.validate(this.username);
  }

  public static Username create(String username) {
    return new Username(username);
  }

  private void validate(String username) {
    if (username.length() < MIN_LENGTH) {
      throw new BadRequestException("length of username must greater than 6 character");
    }
    if (username.length() > MAX_LENGTH) {
      throw new RuntimeException("length of username must less than 12 character");
    }
  }

  public String normalize(String username) {
    return username.trim().toLowerCase();
  }

  public String getValue() {
    return this.username;
  }

}
