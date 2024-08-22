package com.zeroskill.web_bff.dto.response;

import com.zeroskill.common.dto.BannerDto;
import com.zeroskill.common.dto.DiscountedProductDto;

import java.util.List;

public record MainPageResponse(
        List<BannerDto> bannerDtos,
        List<DiscountedProductDto> discountedProductDtos
) {
}
