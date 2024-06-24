package com.zeroskill.buytopia.dto;

import com.zeroskill.buytopia.entity.Term;

import java.time.LocalDate;

public record TermDto(
        Long id,
        String title,
        String version,
        LocalDate createdDate,
        String content,
        String isActive
) {
    public static TermDto of(Term term) {
        return new TermDto(term.getId(), term.getTitle(), term.getVersion(), term.getCreatedDate(), term.getContent(), term.getIsActive());
    }
}
