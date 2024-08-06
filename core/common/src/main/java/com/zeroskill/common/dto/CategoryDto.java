package com.zeroskill.common.dto;

public record CategoryDto(
        Long id,
        String name,
        Long parentCategoryId,
        Long createdBy,
        Long updatedBy
) {
}
