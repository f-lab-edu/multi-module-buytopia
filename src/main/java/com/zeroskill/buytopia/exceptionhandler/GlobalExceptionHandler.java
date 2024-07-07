package com.zeroskill.buytopia.exceptionhandler;

import com.zeroskill.buytopia.dto.response.ApiResponse;
import com.zeroskill.buytopia.exception.BuytopiaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BuytopiaException.class)
    @ResponseBody // TODO: 삭제
    public <T> ResponseEntity<ApiResponse<T>> handleBuytopiaException(BuytopiaException e) {
        return new ResponseEntity<>(ApiResponse.of(e.getCode(), e.getMsg()), e.getHttpStatusCode());
    }
    
    // TODO: Exception 핸들러 추가 (예상치 못한 오류가 발생함.)
    // Exception의 우선순위를 BuytopiaException의 하위로
    // ExceptionHandler 로그찍도록 설정
    // 알람 시스템에 일정 레벨 이상의 에러만 로그를 찍도록 설정
}
