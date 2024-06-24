package com.zeroskill.buytopia.dto.response;

import java.time.LocalDate;

public record GetTermsByTermIdsResponse(
        Long id,
        String title,
        String version,
        LocalDate createdDate,
        String content,
        String isActive
) {
}
