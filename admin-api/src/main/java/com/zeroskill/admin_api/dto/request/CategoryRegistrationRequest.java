package com.zeroskill.admin_api.dto.request;

import com.zeroskill.common.dto.AdminDto;
import com.zeroskill.common.dto.CategoryDto;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record CategoryRegistrationRequest(
        String name
) implements Check  {
    private static final Logger logger = LogManager.getLogger(CategoryRegistrationRequest.class);

    public static CategoryDto toCategoryDto(CategoryRegistrationRequest request) {
        return new CategoryDto(null, request.name(), null, null);
    }

    @Override
    public boolean check() {
        if (name == null || name.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }
        return true;
    }
}
