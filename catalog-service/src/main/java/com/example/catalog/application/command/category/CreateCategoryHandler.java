package com.example.catalog.application.command.category;

import com.example.catalog.domain.category.Category;
import com.example.catalog.domain.category.CategoryRepository;
import com.example.common.command.CommandHandler;
import com.example.common.vm.CommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCategoryHandler implements CommandHandler<CreateCategoryCommand, CommandResult<Long>> {

  private final CategoryRepository categoryRepository;

  @Override
  @Transactional
  public CommandResult<Long> handle(CreateCategoryCommand command) {
    Category category = Category.create(command.getName());
    this.categoryRepository.save(category);

    return CommandResult.of(category.getId());
  }

}
