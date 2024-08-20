package com.zeroskill.commerce_api.dto.request;

import com.zeroskill.common.dto.ProductQuantityDTO;

import java.util.List;

public record AddProductToCartRequest(
        Long memberId,
        List<ProductQuantityDTO> products
) {
}
