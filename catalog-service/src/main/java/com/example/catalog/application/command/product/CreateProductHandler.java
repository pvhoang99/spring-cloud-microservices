package com.example.catalog.application.command.product;

import com.example.catalog.domain.catalog.CategoryService;
import com.example.catalog.domain.product.Product;
import com.example.catalog.domain.product.ProductRepository;
import com.example.common.command.CommandHandler;
import com.example.common.exception.EntityNotFoundException;
import com.example.common.valueobject.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateProductHandler implements CommandHandler<CreateProductCommand, String> {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public String handle(CreateProductCommand command) {
        this.validate(command);
        Product product = Product.create(
            command.getName(),
            command.getDescription(),
            command.getImage(),
            Money.of(command.getMoney()),
            command.getCategoryId()
        );
        this.productRepository.save(product);

        return product.getCode();
    }

    private void validate(CreateProductCommand command) {
        if (!this.categoryService.existById(command.getCategoryId())) {
            throw new EntityNotFoundException("category.not.found");
        }
    }

}
