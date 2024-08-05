package com.zeroskill.common.dto;

import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Category;
import com.zeroskill.common.entity.Discount;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record ProductDto(
        Long id,
        String name,
        String description,
        Long price,
        Integer quantity,
        CategoryDto categoryDto,
        DiscountDto discountDto,
        AdminDto createdBy,
        AdminDto updatedBy
) {
}
