package com.zeroskill.common.service;

import com.zeroskill.common.dto.OrderDto;
import com.zeroskill.common.entity.*;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.repository.CartRepository;
import com.zeroskill.common.repository.DeliveryRepository;
import com.zeroskill.common.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public OrderDto createOrderFromCart(Long memberId, String receiverName, String phoneNumber) {
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

            // 재고 감소
//            product.reduceStock(cartItem.getQuantity());

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
        Order savedOrder = orderRepository.save(order);

        // 6. 결제 요청
        paymentService.processPayment(payment);

//        // 7. 결제 결과에 따른 주문 상태 업데이트 (callback url에 추가)
//        if (paymentDto.paymentStatus() == PaymentStatus.COMPLETED) {
//            order.markAsOrdered();  // 상태 변경을 위한 메서드 사용
//            delivery.startDelivery();  // 결제 완료되면 배달 시작
//
//            // 8. 장바구니 비우기 (결제 성공 후)
//            cart.getItems().clear();  // 장바구니에서 모든 항목 제거
//            cartRepository.save(cart);  // 장바구니 상태 업데이트
//        } else {
//            order.markAsCancelled();  // 상태 변경을 위한 메서드 사용
//            throw new BuytopiaException(ErrorType.PAYMENT_FAILED, logger::error);
//        }

        orderRepository.save(order);
        return OrderDto.of(order);
    }

}
