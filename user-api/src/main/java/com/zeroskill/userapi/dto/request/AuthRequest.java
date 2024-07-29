package com.zeroskill.userapi.dto.request;

public record AuthRequest(
        String loginId,
        String password
) {
}
