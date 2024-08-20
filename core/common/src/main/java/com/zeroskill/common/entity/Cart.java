package com.zeroskill.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CartItem> items = new ArrayList<>();

    public Cart(Member member) {
        this.member = member;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void addCartItem(CartItem cartItem) {
        // CartItem이 이미 존재하는지 확인 (중복 상품 체크)
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
                item.increaseQuantity(cartItem.getQuantity());
                return;
            }
        }
        items.add(cartItem);
        cartItem.assignToCart(this);
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}