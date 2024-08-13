package com.zeroskill.common.entity;

import com.zeroskill.common.dto.DiscountDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "discount")
@Getter
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private Long amount;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    public Discount(DiscountType discountType, Long amount, LocalDate startDate, LocalDate endDate, Admin createdBy, Admin updatedBy) {
        this.discountType = discountType;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public static Discount toEntity(DiscountDto dto, Admin creator, Admin updater) {
        return new Discount(dto.discountType(), dto.amount(), dto.startDate(), dto.endDate(), creator, updater);
    }

    public static DiscountDto of(Discount discount) {
        return new DiscountDto(
                discount.getId(),
                discount.getDiscountType(),
                discount.getAmount(),
                discount.getStartDate(),
                discount.getEndDate(),
                discount.getCreatedBy().getId(),
                discount.getUpdatedBy().getId()
        );
    }
}
