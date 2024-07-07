package com.zeroskill.buytopia.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class TermPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Purpose purpose;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "term_title", referencedColumnName = "title"),
            @JoinColumn(name = "term_version", referencedColumnName = "version")
    })
    private Term term;
}
