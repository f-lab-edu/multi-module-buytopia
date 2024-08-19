package com.zeroskill.admin_api.controller.internal;

import com.zeroskill.admin_api.dto.request.CategoryRegistrationRequest;
import com.zeroskill.common.service.AdminService;
import com.zeroskill.common.service.CategoryService;
import com.zeroskill.common.dto.CategoryDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Category;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private static final Logger logger = LogManager.getLogger(CategoryController.class);

    private final CategoryService categoryService;
    private final AdminService adminService;

    @PostMapping
    public void addCategory(@RequestBody CategoryRegistrationRequest request) {
        request.check();
        Admin admin = adminService.findById(request.createdBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        if (admin == null) {
            throw new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error);
        }

        CategoryDto categoryDto = CategoryRegistrationRequest.toCategoryDto(request);
        categoryService.register(categoryDto);
    }

    @GetMapping
    public ApiResponse<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtos = categoryService.findAll()
                .stream()
                .map(Category::of)
                .toList();
        return new ApiResponse<>(null, null, categoryDtos);
    }
}