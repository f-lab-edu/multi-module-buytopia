package com.zeroskill.user_api.dto.request;

import com.zeroskill.user_api.dto.AddressDto;
import com.zeroskill.user_api.dto.MemberDto;
import com.zeroskill.user_api.entity.Grade;
import com.zeroskill.user_api.exception.UserApiException;
import com.zeroskill.user_api.exception.ErrorType;
import com.zeroskill.user_api.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.zeroskill.user_api.util.Util.isValidEmail;

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
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (name == null || name.isEmpty()) {
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (email == null || email.isEmpty()) {
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (!isValidEmail(email)) {
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (password == null || password.isEmpty()) {
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (passwordConfirm == null || passwordConfirm.isEmpty()) {
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (address == null ||
                address.mainAddress().isEmpty() ||
                address.subAddress().isEmpty() ||
                address.zipcode().isEmpty()) {
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }

        if (!password.equals(passwordConfirm)) {
            throw new UserApiException(ErrorType.PASSWORD_MISS_MATCH, logger::error);
        }

        return true;
    }
}