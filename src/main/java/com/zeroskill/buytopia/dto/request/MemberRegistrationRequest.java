package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.dto.AddressDto;
import com.zeroskill.buytopia.dto.MemberDto;
import com.zeroskill.buytopia.entity.Grade;
import com.zeroskill.buytopia.exception.*;
import com.zeroskill.buytopia.validation.FieldValidatable;
import com.zeroskill.buytopia.validation.PasswordValidatable;

import static com.zeroskill.buytopia.util.Util.isValidEmail;

public record MemberRegistrationRequest(
        String loginId,
        String name,
        String email,
        String password,
        String passwordConfirm,
        AddressDto address
) implements FieldValidatable, PasswordValidatable {
    public static MemberDto toMemberDto(MemberRegistrationRequest request) {
        return new MemberDto(null, request.loginId(), request.name, request.email, request.password, Grade.SILVER, request.address);
    }

    @Override
    public boolean checkEmptyField() {
        if (loginId == null || loginId.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }

        if (name == null || name.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }

        if (email == null || email.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }

        if (!isValidEmail(email)) {
            throw ErrorType.INVALID_EMAIL_FORMAT.exception();
        }

        if (password == null || password.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }

        if (passwordConfirm == null || passwordConfirm.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }

        if (address == null ||
                address.mainAddress().isEmpty() ||
                address.subAddress().isEmpty() ||
                address.zipcode().isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        return true;
    }

    @Override
    // TODO: check interface 구현
    public boolean checkPasswordMatch() {
        if (!password.equals(passwordConfirm)) {
            throw ErrorType.PASSWORD_MISS_MATCH.exception();
        }
        return true;
    }
}