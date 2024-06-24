package com.zeroskill.buytopia.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

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
    private LocalDate agreedDate;
}