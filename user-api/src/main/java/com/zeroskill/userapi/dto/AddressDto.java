package com.zeroskill.userapi.dto;

public record AddressDto(
        String mainAddress,
        String subAddress,
        String zipcode
) {
}
