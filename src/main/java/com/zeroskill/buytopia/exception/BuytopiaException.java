package com.zeroskill.buytopia.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class BuytopiaException extends RuntimeException {
    // TODO: BuytopiaException으로 변경
    public BuytopiaException(String code, String msg, HttpStatusCode httpStatusCode) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.httpStatusCode = httpStatusCode;
    }

    private final String code;
    private final String msg;
    private final HttpStatusCode httpStatusCode;
}