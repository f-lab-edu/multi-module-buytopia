package com.zeroskill.common.util;

import lombok.Getter;

@Getter
public enum UtilConstants {
    SUCCESS("0000"),
    FAILURE("XXXX"); // 나머지 실패 코드를 위한 일반적인 표현

    private final String code;

    UtilConstants(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static boolean fromCode(String code) {
        return "0000".equals(code);
    }
}
