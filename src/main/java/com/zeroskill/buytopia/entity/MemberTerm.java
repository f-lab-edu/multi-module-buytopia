package com.zeroskill.buytopia.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class MemberTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    private boolean agreed;
    private final LocalDateTime agreedDate;

    public MemberTerm(Member member, Term term) {
        this.member = member;
        this.term = term;
        this.agreed = true;
        this.agreedDate = LocalDateTime.now();
    }
}