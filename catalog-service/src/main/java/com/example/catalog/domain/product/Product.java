package com.example.catalog.domain.product;

import com.example.common.domain.AggregateRoot;
import com.example.common.exception.BadRequestException;
import com.example.common.valueobject.Money;
import com.example.common.valueobject.MoneyConverter;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
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
    @Convert(converter = MoneyConverter.class)
    private Money money;

    @Column(name = "catalog_id")
    private String catalogId;

    public static Product create(String name, String description, String image, Money money, String catalogId) {
        Product product = new Product();
        product.generateCode();
        product.setName(name);
        product.setDescription(description);
        product.setImage(image);
        product.setMoney(money);
        product.setCatalogId(catalogId);
        product.dispatchCreatedEvent();

        return product;
    }

    private void dispatchCreatedEvent() {
        this.dispatch(
            ProductCreatedEvent.of(this.code)
        );
    }

    private void generateCode() {
        this.setCode(UUID.randomUUID().toString());
    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new BadRequestException("product.name.not.empty");
        }
        this.name = name;
    }

}
