package com.zeroskill.userapi.dto.response;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
