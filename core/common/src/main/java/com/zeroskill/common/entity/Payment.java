package com.zeroskill.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Payment(PaymentMethod paymentMethod, Order order, PaymentStatus paymentStatus) {
        this.paymentMethod = paymentMethod;
        this.amount = order.getOrderItems().stream().mapToLong(OrderItem::getOrderPrice).sum();
        this.paymentStatus = paymentStatus;
        this.paymentDate = LocalDateTime.now();
        this.order = order;
    }

    // 생성 메서드
    public static Payment createPayment(PaymentMethod method, Order order) {
        return new Payment(method, order, PaymentStatus.PENDING);
    }

    // 결제 성공
    public void completePayment() {
        this.paymentStatus = PaymentStatus.COMPLETED;
    }

    // 결제 실패
    public void failPayment() {
        this.paymentStatus = PaymentStatus.FAILED;
    }
}
