package com.example.shoppingcartservice.dao.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "cart_event", indexes = {
    @Index(name = "IDX_CART_EVENT_USER", columnList = "id,userId")})
@Entity
@Getter
@Setter
public class CartEvent extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Enumerated(EnumType.STRING)
  private CartEventType cartEventType;
  private Long userId;
  private String productId;
  private Integer quantity;

  public CartEvent() {
  }

  public CartEvent(CartEventType cartEventType, Long userId) {
    this.cartEventType = cartEventType;
    this.userId = userId;
  }

  public CartEvent(CartEventType cartEventType, Long userId, String productId, Integer quantity) {
    this.cartEventType = cartEventType;
    this.userId = userId;
    this.productId = productId;
    this.quantity = quantity;
  }

  public enum CartEventType {
    ADD_ITEM,
    REMOVE_ITEM,
    CLEAR_CART,
    CHECKOUT
  }
}
