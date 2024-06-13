package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public class PasswordMissMatchException extends RuntimeException {
    public PasswordMissMatchException(String code, String message) {
        super(message);
        this.code = code;
    }

    private final String code;
}
