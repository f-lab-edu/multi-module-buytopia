package com.zeroskill.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price", nullable = false)
    private Long price;

    public CartItem(Cart cart, Product product, Long quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
    }

    public void increaseQuantity(Long amount) {
        this.quantity += amount;
    }

    public void assignToCart(Cart cart) {
        this.cart = cart;
    }
}