package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.EmptyFieldException;
import com.zeroskill.buytopia.exception.ErrorType;
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
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }
        if (email == null || email.isEmpty()) {
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }
        if (!isValidEmail(email)) {
            throw new InvalidEmailFormatException(ErrorType.INVALID_EMAIL_FORMAT_CD.getData());
        }
        return true;
    }
}
