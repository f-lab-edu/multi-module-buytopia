package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.EmptyFieldException;
import com.zeroskill.buytopia.exception.ErrorCode;
import com.zeroskill.buytopia.exception.ErrorMessage;
import com.zeroskill.buytopia.exception.InvalidEmailFormatException;
import com.zeroskill.buytopia.validation.FieldValidatable;

import static com.zeroskill.buytopia.util.Util.isValidEmail;

public record MemberAvailabilityCheckRequest(
        String loginId,
        String email
) implements FieldValidatable {
    @Override
    public boolean checkEmptyField() {
        if (loginId == null || loginId.isEmpty()) {
            throw new EmptyFieldException(ErrorCode.EMPTY_FIELD.getCode(), ErrorMessage.EMPTY_LOGIN_ID.getMessage());
        }
        if (email == null || email.isEmpty()) {
            throw new EmptyFieldException(ErrorCode.EMPTY_FIELD.getCode(), ErrorMessage.EMPTY_EMAIL.getMessage());
        }
        if (!isValidEmail(email)) {
            throw new InvalidEmailFormatException(ErrorCode.INVALID_EMAIL_FORMAT.getCode(), ErrorMessage.INVALID_EMAIL_FORMAT.getMessage());
        }
        return true;
    }
}
