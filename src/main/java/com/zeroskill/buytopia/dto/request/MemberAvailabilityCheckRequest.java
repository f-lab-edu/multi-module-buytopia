package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.validation.FieldValidatable;

import static com.zeroskill.buytopia.util.Util.isValidEmail;

public record MemberAvailabilityCheckRequest(
        String loginId,
        String email
) implements FieldValidatable {
    @Override
    public boolean checkEmptyField() {
        if (loginId == null || loginId.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        if (email == null || email.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        if (!isValidEmail(email)) {
            throw ErrorType.INVALID_EMAIL_FORMAT.exception();
        }
        return true;
    }
}
