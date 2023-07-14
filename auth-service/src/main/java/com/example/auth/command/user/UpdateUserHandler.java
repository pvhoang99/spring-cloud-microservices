package com.example.auth.command.user;

import com.example.common.command.CommandHandler;
import com.example.common.vm.CommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserHandler implements CommandHandler<UpdateUserCommand, CommandResult<Long>> {


  @Override
  @Transactional
  public CommandResult<Long> handle(UpdateUserCommand command) {

    //đoạn này gọi domain rồi trigger business logic

    return null;
  }

}
