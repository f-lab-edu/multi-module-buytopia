package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String code) {
        super(ErrorType.DUPLICATE_ENTITY_MSG.getData());
        this.code = code;
    }

    private final String code;
}