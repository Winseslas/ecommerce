package com.gdtcore.categorieservice.controller;

import com.gdtcore.categorieservice.model.dto.CategoryRequest;
import com.gdtcore.categorieservice.model.dto.CategoryResponse;
import com.gdtcore.categorieservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryRequest categoryRequest){
        this.categoryService.createCategory(categoryRequest);
    }

    @GetMapping("/read-all")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategories(){
        return this.categoryService.getAllCategories();
    }

    @GetMapping("/read-one/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return this.categoryService.getCategoryById(id);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        this.categoryService.updateCategory(id, categoryRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        this.categoryService.deleteCategory(id);
    }
}
