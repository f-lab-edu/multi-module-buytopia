package com.zeroskill.common.dto;

import com.zeroskill.common.entity.DiscountType;
import com.zeroskill.common.entity.Member;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public record DiscountDto (
        Long id,
        DiscountType discountType,
        Long amount,
        LocalDate startDate,
        LocalDate endDate,
        AdminDto createdBy,
        AdminDto updatedBy
){
}
