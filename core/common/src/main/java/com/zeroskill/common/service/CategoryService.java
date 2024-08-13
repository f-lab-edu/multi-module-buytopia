package com.zeroskill.common.service;

import com.zeroskill.common.dto.CategoryDto;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Category;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.repository.AdminRepository;
import com.zeroskill.common.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final AdminRepository adminRepository;

    public void register(CategoryDto dto) {
        Admin creator = adminRepository.findById(dto.createdBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        Admin updater = adminRepository.findById(dto.updatedBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));

        Category category = null;
        if(dto.parentCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(dto.parentCategoryId()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
            category = Category.toEntity(dto, parentCategory, creator, updater);
        } else {
            category = Category.toEntity(dto, null, creator, updater);
        }
        categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
