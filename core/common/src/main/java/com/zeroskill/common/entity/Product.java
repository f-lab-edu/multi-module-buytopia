package com.zeroskill.common.entity;

import com.zeroskill.common.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "product")
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    public Product(ProductDto dto, Category category, Discount discount, Admin createdBy, Admin updatedBy) {
        this.name = dto.name();
        this.description = dto.description();
        this.price = dto.price();
        this.quantity = dto.quantity();
        this.category = category;
        this.discount = discount;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
