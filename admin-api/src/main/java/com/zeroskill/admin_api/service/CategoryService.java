package com.zeroskill.admin_api.service;

import com.zeroskill.common.dto.AdminDto;
import com.zeroskill.common.dto.CategoryDto;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Category;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zeroskill.common.dto.AdminDto.hashPassword;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public void register(CategoryDto dto) {
        Category category = Category.toEntity(dto);
        categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
