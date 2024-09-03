package com.zeroskill.admin_api.dto.request;

import com.zeroskill.common.dto.ProductDto;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record ProductRegistrationRequest(
    String name,
    String description,
    Long price,
    Long quantity,
    Long categoryId,
    Long discountId,
    Long createdBy,
    Long updatedBy
) implements Check {
    private static final Logger logger = LogManager.getLogger(ProductRegistrationRequest.class);

    public static ProductDto toProductDto(ProductRegistrationRequest request) {
        return new ProductDto(null, request.name(), request.description, request.price, request.quantity, request.categoryId, request.discountId, request.createdBy, request.updatedBy);
    }

    @Override
    public boolean check() {
        if (name == null || name.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (description == null || description.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (price == null || price < 0) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (quantity == null || quantity < 0) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (categoryId == null || categoryId < 0) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (discountId == null || discountId < 0) {
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
