package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String code, String message) {
        super(message);
        this.code = code;
    }

    private final String code;
}
