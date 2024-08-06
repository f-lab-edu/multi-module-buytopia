package com.zeroskill.common.dto;

import com.zeroskill.common.entity.Category;
import com.zeroskill.common.entity.Product;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

public record CategoryDto(
        Long id,
        String name,
        CategoryDto parentCategory,
        List<ProductDto> productDtos
) {
}
