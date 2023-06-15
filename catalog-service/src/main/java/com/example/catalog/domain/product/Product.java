package com.example.catalog.domain.product;

import com.example.common.domain.AggregateRoot;
import com.example.common.exception.BadRequestException;
import com.example.common.valueobject.Money;
import com.example.common.valueobject.MoneyConverter;
import java.util.UUID;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Product extends AggregateRoot {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "category_id")
    private Long categoryId;

    public static Product create(String name, String description, String image, Long catalogId) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImage(image);
        product.setCategoryId(catalogId);

        return product;
    }

    private void dispatchCreatedEvent() {

    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new BadRequestException("product.name.not.empty");
        }
        this.name = name;
    }

}
