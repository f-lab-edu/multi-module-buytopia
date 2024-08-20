package com.zeroskill.admin_api.dto.request;

import com.zeroskill.common.dto.AdminDto;
import com.zeroskill.common.dto.BannerDto;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public record BannerRegistrationRequest(
        String title,
        String imageUrl,
        String linkUrl,
        Date startDate,
        Date endDate,
        String description,
        Long createdBy,
        Long updatedBy
) implements Check {

    private static final Logger logger = LogManager.getLogger(BannerRegistrationRequest.class);

    public static BannerDto toBannerDto(BannerRegistrationRequest request) {
        return new BannerDto(null, request.title(), request.imageUrl, request.linkUrl, null, request.startDate, request.endDate, request.description, request.createdBy, request.updatedBy);
    }
    @Override
    public boolean check() {
        if (title == null || title.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (startDate == null) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (endDate == null) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (createdBy == null || createdBy < 0) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (updatedBy == null || updatedBy < 0) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        return true;
    }
}
