package com.zeroskill.common.dto;

public record OrderItemDto(
        Long productId,
        Long quantity,
        Long orderPrice
) {
    // 기본 생성자 외에 추가적인 로직이 필요하지 않음
}
