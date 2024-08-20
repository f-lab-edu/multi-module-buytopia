package com.zeroskill.common.dto;

import java.util.Date;

public record BannerDto(
        Long id,
        String title,
        String imageUrl,
        String linkUrl,
        Boolean isActive,
        Date startDate,
        Date endDate,
        String description,
        Long createdBy,
        Long updatedBy
) {
}
