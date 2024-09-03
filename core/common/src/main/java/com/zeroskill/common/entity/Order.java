package com.zeroskill.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`order`")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GeneratedValue
    private UUID id;

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

    public Order(Member member) {
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.member = member;
    }

    // 연관 관계 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.assignToOrder(this);
    }

    public static Order createOrder(Member member) {
        return new Order(member);
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

