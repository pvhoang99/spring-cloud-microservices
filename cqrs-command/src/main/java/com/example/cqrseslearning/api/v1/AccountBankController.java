package com.example.cqrseslearning.api.v1;

import com.example.cqrseslearning.account.command.CreateAccountCommand;
import com.example.cqrseslearning.account.command.EditMoneyCommand;
import com.example.cqrseslearning.account.dto.AccountBankDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class AccountBankController {

  private final CommandGateway commandGateway;

  private final EventStore eventStore;

  @PostMapping("/create")
  public void create(@RequestBody AccountBankDto accountBankDto) {
    String id = UUID.randomUUID().toString();
    CreateAccountCommand createAccountCommand = new CreateAccountCommand(id,
        accountBankDto.getOverdraftLimit());

    commandGateway.send(createAccountCommand);
  }

  @GetMapping("/get-event/{id}")
  public List<Object> listEventsForAccount(@PathVariable(value = "id") String accountNumber) {
    return eventStore.readEvents(accountNumber).asStream().map(Message::getPayload).collect(
        Collectors.toList());
  }

  @PutMapping("/edit")
  public void edit(@RequestBody AccountBankDto accountBankDto) {

    commandGateway.send(new EditMoneyCommand(accountBankDto.getId(), accountBankDto.getMoney()));
  }

}
