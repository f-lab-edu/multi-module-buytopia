package com.zeroskill.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static boolean isValidEmail(String email) {
        // 이메일 형식을 검증하는 정규 표현식
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
