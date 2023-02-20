package com.example.inventory.application.command.product;

import com.example.common.command.CommandHandler;
import com.example.inventory.domain.category.Category;
import com.example.inventory.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCategoryHandler implements CommandHandler<CreateCategoryCommand, Long> {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Long handle(CreateCategoryCommand command) {
        Category category = Category.of(command.getName());
        this.categoryRepository.save(category);

        return null;
    }

}
