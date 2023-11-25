package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.common.exception.model.NotFoundException;
import com.server.dosopt.seminar.domain.Category;
import com.server.dosopt.seminar.domain.CategoryId;
import com.server.dosopt.seminar.dto.response.CategoryResponse;
import com.server.dosopt.seminar.repository.CategoryJpaRepository;
import com.server.dosopt.seminar.common.response.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;

    public Category getByCategoryId(CategoryId categoryId) {
        return categoryJpaRepository.findById(Short.valueOf(categoryId.getCategoryId())).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_CATEGORY_EXCEPTION, Error.NOT_FOUND_CATEGORY_EXCEPTION.getMessage()));
    }

    public CategoryResponse getById(Short id) {
        return CategoryResponse.of(categoryJpaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_CATEGORY_EXCEPTION, Error.NOT_FOUND_CATEGORY_EXCEPTION.getMessage())));
    }
}
