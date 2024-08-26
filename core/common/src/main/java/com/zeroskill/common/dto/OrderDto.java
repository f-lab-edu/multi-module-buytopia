package com.zeroskill.common.dto;

import com.zeroskill.common.entity.Order;
import com.zeroskill.common.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record OrderDto(
        Long orderId,
        Long memberId,
        LocalDateTime orderDate,
        OrderStatus status,
        List<OrderItemDto> orderItems
) {

    public static OrderDto of(Order order) {
        return new OrderDto(order.getId(), order.getMember().getId(), order.getOrderDate(), order.getStatus(), order.getOrderItems().stream()  // Stream 처리
                .map(orderItem -> new OrderItemDto(orderItem.getProduct().getId(), orderItem.getQuantity(), orderItem.getOrderPrice()))
                .collect(Collectors.toList()));
    }
}
