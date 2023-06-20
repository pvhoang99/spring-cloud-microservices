package com.example.catalog.application.vm;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductVm {

    private Long id;

    private String name;

    private String description;

    private String image;

    private String catalogId;

    public ProductVm(Long id, String name, String description, String image, String catalogId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.catalogId = catalogId;
    }

}
