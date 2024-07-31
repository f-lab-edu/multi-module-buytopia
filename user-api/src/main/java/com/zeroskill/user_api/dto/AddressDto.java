package com.zeroskill.user_api.dto;

public record AddressDto(
        String mainAddress,
        String subAddress,
        String zipcode
) {
}
