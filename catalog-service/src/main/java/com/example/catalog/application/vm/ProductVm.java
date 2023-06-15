package com.example.catalog.application.vm;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductVm {

    private String name;

    private String description;

    private String image;

    private Long catalogId;

    public ProductVm(String name, String description, String image, Long catalogId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.catalogId = catalogId;
    }
}
