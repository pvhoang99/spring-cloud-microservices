package com.example.catalog.application.vm;

import com.example.common.valueobject.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductVm {

    private String code;

    private String name;

    private String description;

    private String image;

    private Long money;

    private String catalogId;

    public ProductVm(String code, String name, String description, String image, Money money, String catalogId) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.money = money.getValue();
        this.catalogId = catalogId;
    }
}
