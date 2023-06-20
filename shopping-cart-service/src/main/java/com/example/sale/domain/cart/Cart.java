package com.example.sale.domain.cart;

import com.example.common.domain.AggregateRoot;
import com.example.common.exception.BadRequestException;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<CartItem> items;

    private Long total;

    public static Cart createEmpty() {
        Cart cart = new Cart();
        cart.setItems(new HashSet<>());
        cart.setUserId(1L);
        cart.setStatus(Status.ACTIVE);

        return cart;
    }

    public void reloadCart() {

    }

    public void calculatePrice(CartService cartService) {
        Set<Long> productIds = this.items.stream().map(CartItem::getProductId).collect(Collectors.toSet());
        Map<Long, Long> productPrice = cartService.collectPriceProduct(productIds);
    }

    public void addItem(CartItem item) {
        this.items.add(item);
    }

    public void deleteItem(CartItem item) {
        if (item == null || item.getId() == null) {
            throw new BadRequestException("Cart deleteItem exception");
        }
        this.items.remove(item);
    }



}
