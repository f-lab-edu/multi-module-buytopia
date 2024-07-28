package com.zeroskill.buytopia.dto.request;

public record AuthRequest(
        String loginId,
        String password
) {
}
