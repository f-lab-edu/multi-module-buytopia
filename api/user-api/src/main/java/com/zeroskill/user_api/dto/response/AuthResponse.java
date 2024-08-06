package com.zeroskill.user_api.dto.response;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
