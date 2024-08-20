package com.zeroskill.web_bff.dto.request;

import com.zeroskill.common.dto.ProductQuantityDTO;

import java.util.List;

public record AddProductToCartRequest(
        List<ProductQuantityDTO> products
) {
}
