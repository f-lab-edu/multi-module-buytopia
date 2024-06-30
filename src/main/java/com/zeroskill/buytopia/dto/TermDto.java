package com.zeroskill.buytopia.dto;

import com.zeroskill.buytopia.entity.Term;

import java.time.LocalDateTime;

public record TermDto(
        String title,
        String version,
        LocalDateTime createdDate,
        String content,
        String isActive
) {
    public static TermDto of(Term term) {
        return new TermDto(term.getTitle(), term.getVersion(), term.getCreatedDate(), term.getContent(), term.getIsActive());
    }
}
