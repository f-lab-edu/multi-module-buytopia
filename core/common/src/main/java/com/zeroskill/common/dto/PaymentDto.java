package com.zeroskill.common.dto;

import com.zeroskill.common.entity.Payment;
import com.zeroskill.common.entity.PaymentMethod;
import com.zeroskill.common.entity.PaymentStatus;

public record PaymentDto(
        PaymentMethod paymentMethod,
        Long amount,
        PaymentStatus paymentStatus
) {
    public static PaymentDto of(Payment payment) {
        return new PaymentDto(payment.getPaymentMethod(), payment.getAmount(), payment.getPaymentStatus());
    }
}
