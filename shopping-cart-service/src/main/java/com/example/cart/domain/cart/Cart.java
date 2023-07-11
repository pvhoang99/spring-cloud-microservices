package com.example.cart.domain.cart;

import com.example.common.domain.AggregateRoot;
import com.example.common.exception.BadRequestException;
import com.example.cart.infrastructure.client.dto.response.ProductDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Setter
@Getter
@NoArgsConstructor
public class Cart extends AggregateRoot {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Status status;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinTable(name = "cart_item_mapping",
      joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
  @MapKey(name = "id")
  private Map<Long, CartItem> items;

  @Transient
  private Long totalPrice;

  public static Cart createEmpty() {
    Cart cart = new Cart();
    cart.setItems(new HashMap<>());
    cart.setUserId(1L);
    cart.setStatus(Status.ACTIVE);

    return cart;
  }

  public void clear() {
    if (!this.status.equals(Status.ACTIVE)) {
      throw new BadRequestException("Không thể clear cart");
    }
    this.setItems(Map.of());
  }

  public void reloadCart(CartService cartService) {
    Set<Long> productIds = this.items.keySet();
    Map<Long, ProductDTO> productPrice = cartService.collectProduct(productIds);
    this.totalPrice = 0L;
    for (CartItem item : this.items.values()) {
      //TODO: chưa xử lý case không tồn tại productId
      ProductDTO productDTO = productPrice.get(item.getProductId());
      this.totalPrice += productDTO.getPrice() * item.getQuantity();
      item.addInfo(productDTO.getPrice(), productDTO.getImage(), productDTO.getName());
    }
  }

  public void addItem(Long productId, Long quantity) {
    CartItem cartItem = this.items.getOrDefault(productId, CartItem.createNewItem(productId));
    cartItem.addQuantity(quantity);
    this.items.put(productId, cartItem);
  }

  public void subtractItem(Long productId, Long quantity) {
    CartItem cartItem = this.items.get(productId);
    cartItem.subtractQuantity(quantity);
    this.items.put(productId, cartItem);
  }

}