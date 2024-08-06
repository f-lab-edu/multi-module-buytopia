package com.zeroskill.common.entity;

import com.zeroskill.common.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Category parentCategory;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    public Category(String name, Category parentCategory, Admin creator, Admin updater) {
        this.name = name;
        this.parentCategory = parentCategory;
        this.createdBy = creator;
        this.updatedBy = updater;
    }

    public static Category toEntity(CategoryDto dto, Category parentCategory, Admin creator, Admin updater) {
        return new Category(dto.name(), parentCategory, creator, updater);
    }
}