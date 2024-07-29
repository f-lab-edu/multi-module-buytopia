package com.zeroskill.buytopia.dto.response;

public record ApiResponse<T>(String code, String message, T body) {
    public static <T> ApiResponse<T> of(T body) {
        return new ApiResponse<>(null, null, body);
    }
    public static <T> ApiResponse<T> of (String code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}