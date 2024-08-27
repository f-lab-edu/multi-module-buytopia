package com.zeroskill.common.service;

import com.zeroskill.common.dto.OrderDto;
import com.zeroskill.common.dto.PaymentDto;
import com.zeroskill.common.entity.*;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger logger = LogManager.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final PaymentService paymentService;
    private final DeliveryRepository deliveryRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public OrderDto createOrderFromCart(Long memberId, String receiverName, String phoneNumber) {
        // 1. 장바구니 조회
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for member: " + memberId));

        // 2. 주문 생성
        Member member = cart.getMember();
        Order order = new Order();
        order.assignMember(member);  // 연관 관계 메서드 사용

        // 3. 장바구니 항목을 주문 항목으로 변환
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            // 재고가 부족하면 에러 발생
            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }

            // 재고 감소
            product.reduceStock(cartItem.getQuantity());

            // 주문 항목 생성
            OrderItem orderItem = OrderItem.createOrderItem(product, cartItem.getQuantity());
            orderItems.add(orderItem);
            order.addOrderItem(orderItem);  // 연관 관계 메서드 사용
        }

        // 4. 배달 정보 생성 및 저장
        Address address = member.getAddress();
        Delivery delivery = Delivery.createDelivery(receiverName, address, phoneNumber);
        deliveryRepository.save(delivery);
        order.assignDelivery(delivery);  // 연관 관계 메서드 사용

        Long totalAmount = orderItems.stream().mapToLong(OrderItem::getOrderPrice).sum();
        Payment payment = Payment.createPayment(PaymentMethod.CREDIT_CARD, totalAmount);
        Payment savedPayment = paymentRepository.save(payment);  // Payment 엔티티를 먼저 저장

        // 5. 주문 저장
        order.assignPayment(savedPayment);
        orderRepository.save(order);

        // 6. 결제 요청
        PaymentDto paymentDto = paymentService.processPayment(PaymentMethod.CREDIT_CARD, totalAmount);

        // 7. 결제 결과에 따른 주문 상태 업데이트
        if (paymentDto.paymentStatus() == PaymentStatus.COMPLETED) {
            order.markAsOrdered();  // 상태 변경을 위한 메서드 사용
            delivery.startDelivery();  // 결제 완료되면 배달 시작

            // 8. 장바구니 비우기 (결제 성공 후)
            cart.getItems().clear();  // 장바구니에서 모든 항목 제거
            cartRepository.save(cart);  // 장바구니 상태 업데이트
        } else {
            order.markAsCancelled();  // 상태 변경을 위한 메서드 사용
            throw new BuytopiaException(ErrorType.PAYMENT_FAILED, logger::error);
        }

        orderRepository.save(order);
        return OrderDto.of(order);
    }

}
