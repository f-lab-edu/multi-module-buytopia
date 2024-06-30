package com.zeroskill.buytopia.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // TODO: title, version 복합키 설계
    private Long id;
    private String title;
    private String version;
    private LocalDateTime createdDate;
    private String content;
    private String isActive;

    // TODO: 삭제
    @OneToMany(mappedBy = "term")
    private List<Agreement> agreements;
}