package com.example.inventory.domain.product;

import com.example.common.domain.AggregateRoot;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter(AccessLevel.PRIVATE)
public class Product extends AggregateRoot {

    private final static int LENGTH_OF_CODE = 6;

    @Id
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "category_id")
    private String categoryId;

    public static Product of(String name, String categoryId) {
        Product product = new Product();
        String code = UUID.randomUUID().toString();
        product.setCode(code);
        product.setName(name);
        product.setQuantity(0);
        product.setCategoryId(categoryId);
        product.dispatch(
            ProductCreatedEvent.of(code)
        );

        return product;
    }

}
