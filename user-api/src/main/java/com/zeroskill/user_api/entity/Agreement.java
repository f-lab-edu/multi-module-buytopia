package com.zeroskill.user_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(force = true)
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "term_title", referencedColumnName = "title"),
            @JoinColumn(name = "term_version", referencedColumnName = "version")
    })
    private Term term;

    private boolean agreed;
    private final LocalDateTime agreedDate;

    public Agreement(Member member, Term term) {
        this.member = member;
        this.term = term;
        this.agreed = true;
        this.agreedDate = LocalDateTime.now();
    }
}