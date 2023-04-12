package com.example.catalog.application.command.product;

import com.example.catalog.domain.category.Category;
import com.example.catalog.domain.category.CategoryRepository;
import com.example.common.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCategoryHandler implements CommandHandler<CreateCategoryCommand, String> {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public String handle(CreateCategoryCommand command) {
        Category category = Category.of(command.getName());
        this.categoryRepository.save(category);

        return category.getId();
    }

}
