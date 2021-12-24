package com.example.orderservice.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {


    Order findByOrderId(Integer order);
}
