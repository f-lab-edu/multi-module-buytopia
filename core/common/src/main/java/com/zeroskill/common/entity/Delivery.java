package com.zeroskill.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery")
@Getter
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private String receiverName;
    private String address;
    private String phoneNumber;
    private LocalDateTime deliveryDate;

    public Delivery(String receiverName, String address, String phoneNumber, DeliveryStatus deliveryStatus) {
        this.receiverName = receiverName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = LocalDateTime.now();
        this.status = deliveryStatus;
        this.deliveryDate = LocalDateTime.now();
    }

    // 생성 메서드
    public static Delivery createDelivery(String receiverName, String address, String phoneNumber) {
        return new Delivery(receiverName, address, phoneNumber, DeliveryStatus.PENDING);
    }

    // 배달 시작
    public void startDelivery() {
        this.status = DeliveryStatus.SHIPPED;
    }

    // 배달 완료
    public void completeDelivery() {
        this.status = DeliveryStatus.DELIVERED;
    }
}
