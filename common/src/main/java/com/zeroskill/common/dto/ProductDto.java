package com.zeroskill.common.dto;

public record ProductDto(
        Long id,
        String name,
        String description,
        Long price,
        Integer quantity,
        Long categoryId,
        Long discountId,
        Long createdBy,
        Long updatedBy
) {
}
