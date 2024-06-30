package com.zeroskill.buytopia.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@IdClass(TermId.class)
public class Term {

    @Id
    private String title;

    @Id
    private String version;
    private LocalDateTime createdDate;
    private String content;
    private String required;
    private String isActive;
}