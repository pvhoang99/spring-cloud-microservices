package com.example.order.application.command;

import com.example.common.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand, Long> {

    @Override
    @Transactional
    public Long handle(CreateOrderCommand command) {
        return null;
    }

}
