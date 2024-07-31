package com.zeroskill.user_api.dto;

import com.zeroskill.user_api.entity.Term;

import java.time.LocalDateTime;

public record TermDto(
        String title,
        String version,
        LocalDateTime createdDate,
        String content,
        boolean isRequired,
        boolean isActive
) {
    public static TermDto of(Term term) {
        return new TermDto(term.getId().getTitle(), term.getId().getVersion(), term.getCreatedDate(), term.getContent(), term.isRequired(), term.isActive());
    }
}
