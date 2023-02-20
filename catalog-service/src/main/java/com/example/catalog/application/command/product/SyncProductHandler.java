package com.example.catalog.application.command.product;

import com.example.catalog.domain.product.Product;
import com.example.catalog.domain.product.ProductRepository;
import com.example.common.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SyncProductHandler implements CommandHandler<SyncProductCommand, Void> {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Void handle(SyncProductCommand command) {
        Product product = Product.create(
            command.getCode(),
            command.getName(),
            command.getCatalog()
        );
        this.productRepository.save(product);

        return null;
    }
}
