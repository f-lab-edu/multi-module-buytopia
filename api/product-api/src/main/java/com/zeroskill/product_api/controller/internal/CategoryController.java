package com.zeroskill.product_api.controller.internal;

import com.zeroskill.common.dto.CategoryDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Category;
import com.zeroskill.common.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private static final Logger logger = LogManager.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtos = categoryService.findAll()
                .stream()
                .map(Category::of)
                .toList();
        return new ApiResponse<>(null, null, categoryDtos);
    }
}