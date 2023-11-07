package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.domain.Category;
import com.server.dosopt.seminar.domain.CategoryId;
import com.server.dosopt.seminar.dto.response.CategoryResponse;
import com.server.dosopt.seminar.repository.CategoryJpaRepository;
import jakarta.persistence.EntityNotFoundException;
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
                () -> new EntityNotFoundException("해당하는 카테고리가 없습니다."));
    }

    public CategoryResponse getById(Short id) {
        return CategoryResponse.of(categoryJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("해당하는 카테고리가 없습니다.")));
    }
}
