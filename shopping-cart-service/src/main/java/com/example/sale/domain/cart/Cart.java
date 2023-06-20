package com.example.sale.domain.cart;

import com.example.common.domain.AggregateRoot;
import com.example.sale.infrastructure.client.dto.response.ProductDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "cart")
@Setter(AccessLevel.PRIVATE)
@Getter
public class Cart extends AggregateRoot {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(fetch = FetchType.EAGER)
    @MapKey(name = "productId")
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
