package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    EMPTY_LOGIN_ID("사용자 아이디는 필수입니다."),
    EMPTY_NAME("이름은 필수입니다."),
    EMPTY_EMAIL("이메일은 필수입니다."),
    EMPTY_PASSWORD("비밀번호는 필수입니다."),
    EMPTY_PASSWORD_CONFIRM("비밀번호 확인은 필수입니다."),
    EMPTY_ADDRESS("주소 정보중 빈값이 있습니다."),
    INVALID_EMAIL_FORMAT("이메일 형식이 유효하지 않습니다."),
    PASSWORD_MISS_MATCH("비밀번호 확인이 일치하지 않습니다"),
    MEMBER_DUPLICATE("이미 존재하는 회원입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
