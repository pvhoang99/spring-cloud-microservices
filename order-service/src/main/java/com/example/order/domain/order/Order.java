package com.example.order.domain.order;

import com.example.common.domain.AggregateRoot;
import com.example.common.utils.SecurityContext;
import com.example.order.infrastructure.client.dto.CartVm;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.BeanUtils;

import java.util.List;

import static com.example.order.domain.order.OrderStatus.NEW;

@Table(name = "orders")
@Entity
@Getter
@Setter
public class Order extends AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String transactionId;

    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Long cartId;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Item> items;

    public static Order from(CartVm cartVm) {
        List<Item> itemList = cartVm.getItems().stream().map(cartItemVm -> {
                    Item item = new Item();
                    BeanUtils.copyProperties(cartItemVm, item);

                    return item;
                }
        ).toList();

        Order order = new Order();
        order.username = SecurityContext.currentUsername();
        order.transactionId = cartVm.getTransactionId();
        order.totalPrice = cartVm.getTotalPrice();
        order.cartId = cartVm.getId();
        order.status = NEW;
        order.items = itemList;

        return order;
    }
}
