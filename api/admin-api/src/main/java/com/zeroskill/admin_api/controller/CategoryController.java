package com.zeroskill.admin_api.controller;

import com.zeroskill.admin_api.dto.request.AdminRegistrationRequest;
import com.zeroskill.admin_api.dto.request.CategoryRegistrationRequest;
import com.zeroskill.admin_api.service.CategoryService;
import com.zeroskill.common.dto.CategoryDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Category;
import com.zeroskill.common.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public void addCategory(@RequestBody CategoryRegistrationRequest request) {
        CategoryDto categoryDto = CategoryRegistrationRequest.toCategoryDto(request);
        categoryService.register(categoryDto);
    }

    @GetMapping
    public ApiResponse<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return new ApiResponse<>(null, null, categories);
    }
}