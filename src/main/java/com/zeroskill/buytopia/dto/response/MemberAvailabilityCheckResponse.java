package com.zeroskill.buytopia.dto.response;


public record MemberAvailabilityCheckResponse(
        boolean result,
        String resultMsg
) {
}