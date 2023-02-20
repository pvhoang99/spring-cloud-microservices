package com.example.inventory.application.command.inventory;

import com.example.common.command.CommandHandler;
import com.example.inventory.application.dto.InventoryItemDTO;
import com.example.inventory.application.dto.InventoryProductDTO;
import com.example.inventory.domain.inventory.Inventory;
import com.example.inventory.domain.inventory.InventoryItem;
import com.example.inventory.domain.inventory.InventoryProduct;
import com.example.inventory.domain.inventory.InventoryRepository;
import com.example.inventory.domain.product.ProductService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateInventoryHandler implements CommandHandler<CreateInventoryCommand, Void> {

    private final InventoryRepository inventoryRepository;
    private final ProductService productService;

    @Override
    @Transactional
    public Void handle(CreateInventoryCommand command) {
        this.validate(command);
        Inventory inventory = Inventory.of();
        for (InventoryItemDTO item : command.getInventoryItems()) {
            InventoryProduct inventoryProduct = InventoryProduct.of(
                item.getInventoryProduct().getCode(),
                item.getInventoryProduct().getMoney()
                );
            InventoryItem inventoryItem = InventoryItem.of(
                inventoryProduct,
                item.getQuantity(),
                item.getNote()
            );
            inventory.add(inventoryItem);
        }
        inventory.calculateMoney();
        this.inventoryRepository.save(inventory);

        return null;
    }

    private void validate(CreateInventoryCommand command) {
        Set<String> codes = command.getInventoryItems().stream()
            .map(InventoryItemDTO::getInventoryProduct)
            .map(InventoryProductDTO::getCode)
            .collect(Collectors.toSet());
        this.productService.checkExistAll(codes);
    }

}
