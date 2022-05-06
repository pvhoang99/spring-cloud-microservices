package com.example.orderservice.dao.entity;

import com.example.orderservice.dto.Address;
import com.example.orderservice.dto.CartItem;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Order extends BaseEntity {

  @Id
  private ObjectId orderId;
  private Long userId;
  @Transient
  private OrderStatus orderStatus;
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
    PURCHASED,
    CREAT,
    ORDERED,
    SHIPPED,
    DELIVERED
  }
}
