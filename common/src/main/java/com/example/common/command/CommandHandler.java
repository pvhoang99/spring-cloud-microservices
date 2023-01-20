package com.example.common.command;

public interface CommandHandler<C extends Command<R>, R> {

    R handle(C command);

}
