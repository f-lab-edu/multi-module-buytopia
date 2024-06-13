package com.zeroskill.buytopia.dto.response;

public record ApiResponse<T>(String code, String message, T body) {
}