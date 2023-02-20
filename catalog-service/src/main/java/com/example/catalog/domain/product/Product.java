package com.example.catalog.domain.product;

import com.example.common.domain.AggregateRoot;
import com.example.common.exception.BadRequestException;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Product extends AggregateRoot {

    @Id
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "money")
    private BigDecimal money;

    private String category;

    public static Product create(String code, String name, String category) {
        Product product = new Product();
        product.setCode(code);
        product.setName(name);
        product.setCategory(category);

        return product;
    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new BadRequestException("product.name.not.empty");
        }
        this.name = name;
    }

}
