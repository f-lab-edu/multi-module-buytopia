package com.zeroskill.common.entity;

import com.zeroskill.common.dto.AdminDto;
import com.zeroskill.common.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Category parentCategory;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(String name) {
        this.name = name;
    }

    public static Category toEntity(CategoryDto dto) {
        return new Category(dto.name());
    }
}