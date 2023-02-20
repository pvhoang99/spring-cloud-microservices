package com.example.inventory.application.command.product;

import com.example.common.command.CommandHandler;
import com.example.common.exception.EntityNotFoundException;
import com.example.inventory.domain.category.CategoryService;
import com.example.inventory.domain.product.Product;
import com.example.inventory.domain.product.ProductRepository;
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

        Product product = Product.of(command.getName(), command.getCategoryId());
        this.productRepository.save(product);

        return product.getCode();
    }

    private void validate(CreateProductCommand command) {
        if(!this.categoryService.existById(command.getCategoryId())) {
            throw new EntityNotFoundException("category.not.found");
        }
    }

}
