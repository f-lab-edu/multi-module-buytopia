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
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }

        if (name == null || name.isEmpty()) {
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }

        if (email == null || email.isEmpty()) {
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }

        if (!isValidEmail(email)) {
            throw new InvalidEmailFormatException(ErrorType.INVALID_EMAIL_FORMAT_CD.getData());
        }

        if (password == null || password.isEmpty()) {
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }

        if (passwordConfirm == null || passwordConfirm.isEmpty()) {
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }

        if (address == null ||
                address.mainAddress().isEmpty() ||
                address.subAddress().isEmpty() ||
                address.zipcode().isEmpty()) {
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }
        return true;
    }

    @Override
    public boolean checkPasswordMatch() {
        if (!password.equals(passwordConfirm)) {
            throw new PasswordMissMatchException(ErrorType.PASSWORD_MISS_MATCH_CD.getData());
        }
        return true;
    }
}