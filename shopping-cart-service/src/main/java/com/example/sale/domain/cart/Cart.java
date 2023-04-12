package com.example.sale.domain.cart;

import com.example.common.domain.AggregateRoot;
import com.example.common.exception.BadRequestException;
import com.example.sale.domain.cart.event.CartApprovedEvent;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Cart extends AggregateRoot {

    @Id
    private Long id;

    private Long userId;

    @Embedded
    private SupervisionStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    public static Cart create() {
        final Cart cart = new Cart();
        cart.userId = 0L;
        cart.status = SupervisionStatus.create();
        cart.cartItems = new ArrayList<>();

        return cart;
    }

    public void addCartItem(final CartItem cartItem) {
        this.assertStatusIsPending();
        if (this.cartItems.size() > 10) {
            throw new BadRequestException("");
        }
        this.cartItems.add(cartItem);
    }

    public void removeCartItem(final CartItem cartItem) {
        this.assertStatusIsPending();
        this.cartItems.remove(cartItem);
    }

    public void editCartItem(final Long cartItemId, final Long quantity) {
        this.assertStatusIsPending();
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getId().equals(cartItemId)) {
                cartItem.updateQuantity(quantity);

                return;
            }
        }
    }

    public void approve() {
        this.assertStatusIsPending();
        this.status = SupervisionStatus.approve();
        super.dispatch(CartApprovedEvent.of(this.id));
    }

    public void deny() {
        this.assertStatusIsPending();
        this.status = SupervisionStatus.deny();
        super.dispatch(CartApprovedEvent.of(this.id));
    }

    private void assertStatusIsPending() {
        if (!Status.PENDING.equals(this.status.getStatus())) {
            throw new BadRequestException("");
        }
    }

}
