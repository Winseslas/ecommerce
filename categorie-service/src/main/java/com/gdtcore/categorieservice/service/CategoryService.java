package com.gdtcore.categorieservice.service;

import com.gdtcore.categorieservice.exception.CategoryNotFoundException;
import com.gdtcore.categorieservice.model.dto.CategoryRequest;
import com.gdtcore.categorieservice.model.dto.CategoryResponse;
import com.gdtcore.categorieservice.model.entitie.Category;
import com.gdtcore.categorieservice.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public void createCategory(CategoryRequest categoryRequest){
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
        this.categoryRepository.save(category);
        log.info("Category {} is saved", category.getId());
        return;
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> allCategories = this.categoryRepository.findAll();
        return allCategories.stream().map(this::mapToCategoryResponse).toList();
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        return mapToCategoryResponse(category);
    }

    public void updateCategory(Long id, CategoryRequest categoryRequest) {
        Category existingCategory = this.categoryRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        if (categoryRequest.getName() != null) {
            existingCategory.setName(categoryRequest.getName());
        }
        if (categoryRequest.getDescription() != null) {
            existingCategory.setDescription(categoryRequest.getDescription());
        }
        this.categoryRepository.save(existingCategory);
        log.info("Category {} is updated", existingCategory.getId());
    }

    public void deleteCategory(Long id) {
        Category existingCategory = this.categoryRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        this.categoryRepository.delete(existingCategory);
        log.info("Category {} is deleted", id);
    }
    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
