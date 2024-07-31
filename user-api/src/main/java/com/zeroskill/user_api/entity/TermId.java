package com.zeroskill.user_api.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
// TODO: Term의 innerClass를 넣어줌
public class TermId implements Serializable {
    private String title;
    private String version;

    public TermId(String title, String version) {
        this.title = title;
        this.version = version;
    }
}