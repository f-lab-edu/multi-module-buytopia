package com.zeroskill.admin_api.controller;

import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Category;
import com.zeroskill.common.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @PostMapping
    public void addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
    }

    @GetMapping
    public ApiResponse<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return new ApiResponse<>(null, null, categories);
    }
}