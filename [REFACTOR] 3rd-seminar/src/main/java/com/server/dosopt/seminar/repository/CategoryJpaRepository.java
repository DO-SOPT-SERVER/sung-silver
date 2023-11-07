package com.server.dosopt.seminar.repository;

import com.server.dosopt.seminar.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Short>{
}
