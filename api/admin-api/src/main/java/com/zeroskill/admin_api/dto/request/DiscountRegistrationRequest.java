package com.zeroskill.admin_api.dto.request;

import com.zeroskill.common.dto.DiscountDto;
import com.zeroskill.common.entity.DiscountType;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public record DiscountRegistrationRequest(
    DiscountType discountType,
    Long amount,
    LocalDate startDate,
    LocalDate endDate,
    Long createdBy,
    Long updatedBy
) implements Check {
    private static final Logger logger = LogManager.getLogger(DiscountRegistrationRequest.class);

    public static DiscountDto toDiscountDto(DiscountRegistrationRequest request) {
        return new DiscountDto(null, request.discountType, request.amount, request.startDate, request.endDate, request.createdBy, request.updatedBy);
    }

    @Override
    public boolean check() {
        if (discountType == null) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (amount == null ) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (startDate == null) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (endDate == null) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (createdBy == null) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (updatedBy == null) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        return true;
    }
}
