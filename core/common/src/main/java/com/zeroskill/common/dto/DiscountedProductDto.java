package com.zeroskill.common.dto;

import com.zeroskill.common.entity.DiscountType;

public record DiscountedProductDto(
        Long id,
        String name,
        String description,
        Long price,
        Long discountRate,
        Long discountPrice,
        Long categoryId,
        String categoryName,
        String parentCategoryName,
        DiscountType discountType
) {
}

