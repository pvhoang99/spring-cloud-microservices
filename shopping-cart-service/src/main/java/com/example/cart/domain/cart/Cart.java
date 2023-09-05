package com.example.cart.domain.cart;

import com.example.cart.domain.cart.event.CartConfirmedEvent;
import com.example.cart.infrastructure.client.dto.response.ProductDTO;
import com.example.common.domain.AggregateRoot;
import com.example.common.exception.BadRequestException;
import com.example.common.utils.SecurityContext;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cart")
@Setter
@Getter
@NoArgsConstructor
public class Cart extends AggregateRoot {
    @Serial
    private static final long serialVersionUID = -7842535490839976235L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "transaction_id", updatable = false, unique = true)
    private String transactionId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(name = "cart_item_mapping",
            joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")}
    )
    @MapKey(name = "id")
    private Map<Long, CartItem> items;

    @Transient
    private Long totalPrice;

    public static Cart createEmpty() {
        Cart cart = new Cart();
        cart.setItems(new HashMap<>());
        cart.setUsername(SecurityContext.currentUsername());
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
            boolean isItemNotExist = !productPrice.containsKey(item.getId());
            if (isItemNotExist) {
                this.items.remove(item.getId());

                continue;
            }
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
        boolean isRemoveItem = cartItem.getQuantity() == 0L;
        if (isRemoveItem) {
            this.items.remove(productId);
        } else {
            this.items.put(productId, cartItem);
        }
    }

    public void confirmCart() {
        if (!this.status.equals(Status.ACTIVE)) {
            throw new BadRequestException("Không thể clear cart");
        }
        this.status = Status.CONFIRMED;
        this.transactionId = UUID.randomUUID().toString();
        this.dispatch(CartConfirmedEvent.of(this));
    }
}
