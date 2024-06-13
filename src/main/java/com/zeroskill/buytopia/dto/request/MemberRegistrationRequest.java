package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.dto.AddressDto;
import com.zeroskill.buytopia.dto.MemberDto;
import com.zeroskill.buytopia.exception.EmptyFieldException;
import com.zeroskill.buytopia.exception.ErrorMessage;
import com.zeroskill.buytopia.exception.InvalidEmailFormatException;
import com.zeroskill.buytopia.exception.PasswordMismatchException;
import com.zeroskill.buytopia.validation.FieldValidatable;
import com.zeroskill.buytopia.validation.PasswordValidatable;

import static com.zeroskill.buytopia.util.Util.isValidEmail;

public record MemberRegistrationRequest (
        String loginId,
        String name,
        String email,
        String password,
        String passwordConfirm,
        AddressDto address
) implements FieldValidatable, PasswordValidatable {
    public static MemberDto toMemberDto(MemberRegistrationRequest request) {
        return new MemberDto(null, request.loginId(), request.name, request.email, request.password, (byte) 1, request.address);
    }

    @Override
    public boolean checkEmptyField() {
        if(loginId == null || loginId.isEmpty()) {
            throw new EmptyFieldException(ErrorMessage.EMPTY_LOGIN_ID.getMessage());
        }

        if(name == null || name.isEmpty()) {
            throw new EmptyFieldException(ErrorMessage.EMPTY_NAME.getMessage());
        }

        if(email == null || email.isEmpty()) {
            throw new EmptyFieldException(ErrorMessage.EMPTY_EMAIL.getMessage());
        }

        if (!isValidEmail(email)) {
            throw new InvalidEmailFormatException(ErrorMessage.INVALID_EMAIL_FORMAT.getMessage());
        }

        if(password == null || password.isEmpty()) {
            throw new EmptyFieldException(ErrorMessage.EMPTY_PASSWORD.getMessage());
        }

        if(passwordConfirm == null || passwordConfirm.isEmpty()) {
            throw new EmptyFieldException(ErrorMessage.EMPTY_PASSWORD_CONFIRM.getMessage());
        }

        if (address == null ||
                address.mainAddress().isEmpty() ||
                address.subAddress().isEmpty() ||
                address.zipcode().isEmpty()) {
            throw new EmptyFieldException(ErrorMessage.EMPTY_ADDRESS.getMessage());
        }
        return true;
    }

    @Override
    public boolean checkPasswordMatch() {
        if(!password.equals(passwordConfirm)) {
            throw new PasswordMismatchException(ErrorMessage.PASSWORD_MISS_MATCH.getMessage());
        }
        return true;
    }
}
