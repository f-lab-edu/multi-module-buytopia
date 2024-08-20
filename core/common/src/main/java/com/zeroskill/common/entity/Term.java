package com.zeroskill.common.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity
//TODO: Id 두개를 사용했을때 index가 제대로 타지는지 체크해보기
public class Term {

    @EmbeddedId
    private TermId id;
    private LocalDateTime createdDate;
    private String content;
    private Boolean required;
    private Boolean isActive;

    @OneToMany(mappedBy = "term")
    private Set<TermPurpose> termPurposes;

    // TODO: static으로 생성해줘야됨
}