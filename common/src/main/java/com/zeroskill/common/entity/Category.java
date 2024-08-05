package com.zeroskill.common.entity;

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
}