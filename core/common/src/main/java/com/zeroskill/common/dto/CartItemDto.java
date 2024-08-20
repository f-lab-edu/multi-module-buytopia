package com.zeroskill.common.dto;

public record CartItemDto(
        Long cartId,
        Long productId,
        Long quantity,
        Long price
) {
}
