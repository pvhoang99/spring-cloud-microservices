package com.example.catalog.application.command.product;

import com.example.catalog.domain.category.CategoryService;
import com.example.catalog.domain.product.Product;
import com.example.catalog.domain.product.ProductRepository;
import com.example.common.command.CommandHandler;
import com.example.common.exception.EntityNotFoundException;
import com.example.common.vm.CommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateProductHandler implements CommandHandler<CreateProductCommand, CommandResult<Long>> {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public CommandResult<Long> handle(CreateProductCommand command) {
        this.validate(command);
        Product product = Product.create(command.getName(), command.getPrice(), command.getImage(),
            command.getDescription(), command.getCategoryId());
        this.productRepository.save(product);

        return CommandResult.of(product.getId());
    }

    private void validate(CreateProductCommand command) {
        if (!this.categoryService.existById(command.getCategoryId())) {
            throw new EntityNotFoundException("category.not.found");
        }
    }

}
