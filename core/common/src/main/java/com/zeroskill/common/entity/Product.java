package com.zeroskill.common.entity;

import com.zeroskill.common.dto.ProductDto;
import com.zeroskill.common.exception.BuytopiaException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.zeroskill.common.exception.ErrorType.PRODUCT_OUT_OF_STOCK;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor
public class Product {
    private static final Logger logger = LogManager.getLogger(Product.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long price;
    private Long quantity;

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

    public static ProductDto of(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId(),
                product.getDiscount().getId(),
                product.getCreatedBy().getId(),
                product.getUpdatedBy().getId()
        );
    }

    public void reduceStock(Long quantity) {
        if (this.quantity < quantity) {
            throw new BuytopiaException(PRODUCT_OUT_OF_STOCK, logger::error);
        }
        this.quantity -= quantity;
    }

    // 재고를 증가시키는 메서드 (옵션)
    public void increaseStock(Long quantity) {
        this.quantity += quantity;
    }
}
