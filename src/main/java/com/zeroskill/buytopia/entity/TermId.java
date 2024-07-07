package com.zeroskill.buytopia.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Embeddable
public class TermId implements Serializable {
    private String title;
    private String version;

    public TermId() {}

    public TermId(String title, String version) {
        this.title = title;
        this.version = version;
    }
}