package com.zeroskill.common.dto;

import com.zeroskill.common.entity.DiscountType;

import java.time.LocalDate;

public record DiscountDto (
        Long id,
        DiscountType discountType,
        Long amount,
        LocalDate startDate,
        LocalDate endDate,
        Long createdBy,
        Long updatedBy
){
}
