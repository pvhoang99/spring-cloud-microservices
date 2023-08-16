package com.example.catalog.domain.product;

import com.example.common.domain.AggregateRoot;
import com.example.common.exception.BadRequestException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "category_id")
    private Long categoryId;

    public static Product create(String name, Long price, String description, String image, Long categoryId) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);
        product.setCategoryId(categoryId);

        return product;
    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new BadRequestException("product.name.not.empty");
        }
        this.name = name;
    }
}
