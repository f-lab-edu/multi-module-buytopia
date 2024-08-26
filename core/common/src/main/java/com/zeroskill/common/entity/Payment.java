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

    public Payment(PaymentMethod paymentMethod, Long amount, PaymentStatus paymentStatus) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = LocalDateTime.now();
    }

    // 생성 메서드
    public static Payment createPayment(PaymentMethod method, Long amount) {
        return new Payment(method, amount, PaymentStatus.PENDING);
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
