package com.example.catalog.domain.catalog;

import com.example.catalog.infrastructure.repository.jpa.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final JpaCategoryRepository jpaCategoryRepository;

    public boolean existById(String id) {
        return jpaCategoryRepository.existsById(id);
    }

}
