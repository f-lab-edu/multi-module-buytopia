package com.zeroskill.common.dto.event;

public record PaymentResultEvent(Long paymentId, Long orderId, boolean success) {}

