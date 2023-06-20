package com.example.common.vm;

import lombok.Getter;

@Getter
public class CommandResult<T> {

  private T id;

  public static <T> CommandResult<T> of(T id) {
    CommandResult<T> result = new CommandResult<>();
    result.id = id;

    return result;
  }

}
