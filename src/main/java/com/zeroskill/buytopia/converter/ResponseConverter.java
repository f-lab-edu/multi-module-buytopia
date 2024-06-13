package com.zeroskill.buytopia.converter;

import com.zeroskill.buytopia.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseConverter {
    public static <T> ResponseEntity<ApiResponse<T>> convertToSuccessResponseEntity(T response) {
        ApiResponse<T> apiResponse = new ApiResponse<>(null, null, response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> convertToBadRequest(String errorCode, String errorMessage) {
        ApiResponse<T> apiResponse = new ApiResponse<>(errorCode, errorMessage, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
