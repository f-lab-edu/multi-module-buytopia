package com.zeroskill.common.dto;

public record AddressDto(
        String mainAddress,
        String subAddress,
        String zipcode
) {
}
