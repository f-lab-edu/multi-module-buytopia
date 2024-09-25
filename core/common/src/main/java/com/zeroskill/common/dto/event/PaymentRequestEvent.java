package com.zeroskill.common.dto.event;

import java.util.UUID;

public record PaymentRequestEvent(Long paymentId, UUID orderId, Long amount) {}

