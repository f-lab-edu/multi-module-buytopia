package com.zeroskill.common.dto;

import com.zeroskill.common.entity.Address;

public record AddressDto(
        String mainAddress,
        String subAddress,
        String zipcode
) {
    public static AddressDto of(Address address) {
        return new AddressDto(address.getMainAddress(), address.getSubAddress(), address.getZipcode());
    }
}
