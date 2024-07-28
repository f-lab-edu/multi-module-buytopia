package com.zeroskill.buytopia.dto.response;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
