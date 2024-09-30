package com.zeroskill.common.service;

import com.zeroskill.common.dto.event.PaymentRequestEvent;
import com.zeroskill.common.entity.*;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.repository.CartRepository;
import com.zeroskill.common.repository.DeliveryRepository;
import com.zeroskill.common.repository.OrderRepository;
import com.zeroskill.common.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.zeroskill.common.exception.ErrorType.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger logger = LogManager.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final PaymentService paymentService;
    private final DeliveryRepository deliveryRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PaymentRepository paymentRepository;

    @Value("${topics.payment-request}")
    private String paymentRequestTopic;

    public void createOrderFromCart(Long memberId, String receiverName, String phoneNumber) {
        // 1. 장바구니 조회
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new BuytopiaException(DATA_NOT_FOUND, logger::error));

        if(cart.getItems().isEmpty()) {
            throw new BuytopiaException(CART_EMPTY, logger::error);
        }

        // 2. 주문 생성
        Member member = cart.getMember();
        Order order = Order.createOrder(member);

        // 3. 장바구니 항목을 주문 항목으로 변환
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            // 재고가 부족하면 에러 발생
            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new BuytopiaException(PRODUCT_OUT_OF_STOCK, logger::error);
            }

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

        Payment payment = Payment.createPayment(PaymentMethod.CREDIT_CARD, order);

        // 5. 주문 저장
        order.assignPayment(payment);
        orderRepository.save(order);
        paymentRepository.save(payment);

        // 6. 결제 요청
        PaymentRequestEvent paymentRequestEvent = new PaymentRequestEvent(payment.getId(), order.getId(), memberId);
        logger.info("paymentRequestEvent: {}", paymentRequestEvent);
        kafkaTemplate.send(paymentRequestTopic, paymentRequestEvent);

        logger.info("Payment request event sent to Kafka on topic: {} for paymentId: {}", paymentRequestTopic, payment.getId());
    }
}
