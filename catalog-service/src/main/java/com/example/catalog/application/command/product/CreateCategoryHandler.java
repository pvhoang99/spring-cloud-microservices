package com.example.catalog.application.command.product;

import com.example.catalog.domain.catalog.Category;
import com.example.catalog.domain.catalog.CategoryRepository;
import com.example.common.command.CommandHandler;
import lombok.RequiredArgsConstructor;
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
