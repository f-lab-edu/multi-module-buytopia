package com.zeroskill.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    public Order() {
        this.status = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
    }

    public Order(Member member, Delivery delivery, OrderStatus orderStatus) {
        this.orderDate = LocalDateTime.now();
        this.status = orderStatus;
        this.member = member;
        this.delivery = delivery;
    }

    // 연관 관계 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.assignToOrder(this);
    }

    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems) {
        Order order = new Order(member, delivery, OrderStatus.ORDERED);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    public void assignMember(Member member) {
        this.member = member;
    }

    public void assignPayment(Payment payment) {
        this.payment = payment;
    }

    public void assignDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void markAsOrdered() {
        this.status = OrderStatus.ORDERED;
    }

    public void markAsCancelled() {
        this.status = OrderStatus.CANCELLED;
    }
}

