package com.example.order.application.command;

import com.example.common.command.CommandHandler;
import com.example.order.infrastructure.client.CartServiceFeignClient;
import com.example.order.infrastructure.client.dto.CartVm;
import com.example.order.domain.order.Order;
import com.example.order.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand, Long> {

    private final CartServiceFeignClient cartServiceFeignClient;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Long handle(CreateOrderCommand command) {
        String transactionId = command.getTransactionId();
        CartVm cartVm = this.cartServiceFeignClient.getCartByTransactionId(transactionId);
        cartVm.setTransactionId(transactionId);
        Order order = Order.from(cartVm);
        this.orderRepository.save(order);

        return order.getId();
    }

}
