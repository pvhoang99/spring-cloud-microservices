package com.example.orderservice.dao.entity;

import com.example.common.domain.AggregateRoot;
import com.example.orderservice.dto.Address;
import com.example.orderservice.dto.CartItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Order extends AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private OrderStatus status;
    private Long cartId;

    private List<CartItem> cartItems = new ArrayList<>();
    private Address shippingAddress;

    public Order() {
        this.orderStatus = OrderStatus.PURCHASED;
    }

    public enum OrderStatus {
        NEW,
        PURCHASED,
        PENDING,
        SUCCESS,
        FAIL,
        CONFIRMED,
        SHIPPED,
        DELIVERED
    }

    public enum OrderEvents {
        PAYED,
        PURCHASED,
        CREAT,
        ORDERED,
        SHIPPED,
        DELIVERED
    }
}
