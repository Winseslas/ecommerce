package com.gdtcore.categorieservice.repository;

import com.gdtcore.categorieservice.model.entitie.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
