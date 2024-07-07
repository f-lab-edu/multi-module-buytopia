package com.zeroskill.buytopia.entity;

import java.io.Serializable;
import java.util.Objects;

public class TermId implements Serializable {
    private String title;
    private String version;

    public TermId() {}

    public TermId(String title, String version) {
        this.title = title;
        this.version = version;
    }

    // Getters, Setters, hashCode, equals methods

    // TODO: equals와 hashCode를 override할때의 주의점
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TermId termId = (TermId) o;
        return Objects.equals(title, termId.title) &&
                Objects.equals(version, termId.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, version);
    }
}