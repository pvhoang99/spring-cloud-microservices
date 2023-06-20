package com.example.common.command;

public interface CommandBus {

  <C extends Command<R>, R> R execute(C command);

}
