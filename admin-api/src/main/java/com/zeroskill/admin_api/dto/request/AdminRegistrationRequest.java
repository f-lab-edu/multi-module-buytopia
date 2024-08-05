package com.zeroskill.admin_api.dto.request;

import com.zeroskill.common.dto.AdminDto;
import com.zeroskill.common.dto.MemberDto;
import com.zeroskill.common.entity.Grade;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.validation.Check;
import jakarta.persistence.Column;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.zeroskill.common.util.Util.isValidEmail;

public record AdminRegistrationRequest(
        String loginId,
        String name,
        String email,
        String password,
        String passwordConfirm

) implements Check {
    private static final Logger logger = LogManager.getLogger(AdminRegistrationRequest.class);

    public static AdminDto toAdminDto(AdminRegistrationRequest request) {
        return new AdminDto(null, request.loginId(), request.name, request.email, request.password);
    }

    @Override
    public boolean check() {
        if (loginId == null || loginId.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }
        if (name == null || name.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (email == null || email.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (!isValidEmail(email)) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (password == null || password.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (passwordConfirm == null || passwordConfirm.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        return true;
    }
}
