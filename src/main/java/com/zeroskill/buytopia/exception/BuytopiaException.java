package com.zeroskill.buytopia.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
// TODO: 생성자 2개 만들기, log Level을 받고 log Level이 주어져있으면 log Level 출력, 아니면 그냥 생성자 사용
// logLevel에 따라서 주입
//
public class BuytopiaException extends RuntimeException {
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