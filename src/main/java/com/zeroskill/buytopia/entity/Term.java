package com.zeroskill.buytopia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity
@IdClass(TermId.class)
// TODO: embeddable을 활용한 복합키로 리팩토링
// Id 두개를 사용했을때 index가 제대로 타지는지 체크해보기
public class Term {

    @Id
    private String title;

    @Id
    private String version;
    private LocalDateTime createdDate;
    private String content;
    private boolean required;
    private boolean isActive;

    @OneToMany(mappedBy = "term")
    private Set<TermPurpose> termPurposes;
}