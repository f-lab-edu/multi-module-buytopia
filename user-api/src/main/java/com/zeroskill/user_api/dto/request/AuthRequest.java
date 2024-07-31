package com.zeroskill.user_api.dto.request;

public record AuthRequest(
        String loginId,
        String password
) {
}
