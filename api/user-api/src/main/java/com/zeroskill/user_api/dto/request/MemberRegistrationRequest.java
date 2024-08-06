package com.zeroskill.user_api.dto.request;

import com.zeroskill.common.dto.AddressDto;
import com.zeroskill.common.dto.MemberDto;
import com.zeroskill.common.entity.Grade;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.zeroskill.common.util.Util.isValidEmail;

public record MemberRegistrationRequest(
        String loginId,
        String name,
        String email,
        String password,
        String passwordConfirm,
        AddressDto address
) implements Check {
    private static final Logger logger = LogManager.getLogger(MemberRegistrationRequest.class);

    public static MemberDto toMemberDto(MemberRegistrationRequest request) {
        return new MemberDto(null, request.loginId(), request.name, request.email, request.password, Grade.SILVER, request.address);
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

        if (address == null ||
                address.mainAddress().isEmpty() ||
                address.subAddress().isEmpty() ||
                address.zipcode().isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (!password.equals(passwordConfirm)) {
            throw new BuytopiaException(ErrorType.PASSWORD_MISS_MATCH, logger::error);
        }

        return true;
    }
}