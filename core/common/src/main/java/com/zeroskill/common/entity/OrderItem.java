package com.zeroskill.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, columnDefinition = "BINARY(16)")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Long quantity;
    private Long orderPrice;

    public OrderItem(Product product, Long quantity, Long totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.orderPrice = totalPrice;
    }

    // 생성 메서드
    public static OrderItem createOrderItem(Product product, Long quantity) {
        return new OrderItem(product, quantity, product.getPrice() * quantity);
    }

    public void assignToOrder(Order order) {
        this.order = order;
    }
}
