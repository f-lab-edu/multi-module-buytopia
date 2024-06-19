package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String code) {
        super(ErrorType.EMPTY_FIELD_MSG.getData());
        this.code = code;
    }

    private final String code;
}