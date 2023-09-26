package com.example.order.infrastructure.repository;

import com.example.order.domain.order.Order;
import com.example.order.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderMysqlRepository implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public void save(Order order) {
        this.orderJpaRepository.save(order);
    }
}
