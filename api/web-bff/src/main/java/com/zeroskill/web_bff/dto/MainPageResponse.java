package com.zeroskill.web_bff.dto;

import com.zeroskill.common.dto.DiscountedProductDto;

import java.util.List;

public record MainPageResponse(
        List<DiscountedProductDto> discountedProductDtos
) {
}
