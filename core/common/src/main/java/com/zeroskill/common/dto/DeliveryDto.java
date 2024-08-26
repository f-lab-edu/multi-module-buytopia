package com.zeroskill.common.dto;

import com.zeroskill.common.entity.Delivery;
import com.zeroskill.common.entity.DeliveryStatus;

import java.time.LocalDateTime;

public record DeliveryDto(
        Long deliveryId,
        DeliveryStatus status,
        String receiverName,
        String address,
        String phoneNumber,
        LocalDateTime deliveryDate
) {
    // 생성자: Delivery 객체를 받아 record를 생성하는 팩토리 메서드
    public DeliveryDto(Delivery delivery) {
        this(
                delivery.getId(),
                delivery.getStatus(),
                delivery.getReceiverName(),
                delivery.getAddress(),
                delivery.getPhoneNumber(),
                delivery.getDeliveryDate()
        );
    }
}
