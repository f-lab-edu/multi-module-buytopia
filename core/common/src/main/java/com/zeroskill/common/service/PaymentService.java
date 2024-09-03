package com.zeroskill.common.service;

import com.zeroskill.common.dto.request.PaymentResultRequest;
import com.zeroskill.common.entity.Cart;
import com.zeroskill.common.entity.Order;
import com.zeroskill.common.entity.Payment;
import com.zeroskill.common.entity.Product;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.repository.CartRepository;
import com.zeroskill.common.repository.OrderRepository;
import com.zeroskill.common.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.zeroskill.common.exception.ErrorType.DATA_NOT_FOUND;
import static com.zeroskill.common.exception.ErrorType.PRODUCT_OUT_OF_STOCK;
import static com.zeroskill.common.util.UtilConstants.fromCode;

@Service
public class PaymentService {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository, CartRepository cartRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void completePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        payment.completePayment();
    }

    @Transactional
    public void failPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        payment.failPayment();
    }

    @Transactional
    public void processPayment(Payment payment) {
        // PENDING 상태로 결제 생성

        // 실제 결제 처리 로직을 여기에 추가 (예: 외부 결제 게이트웨이 통신)
        // 예시로 결제가 항상 성공하는 시나리오
    }

    @Transactional
    public void handlePaymentCallback(PaymentResultRequest request) {
        Order order = orderRepository.findById(UUID.fromString(request.orderId()))
                .orElseThrow(() -> new BuytopiaException(DATA_NOT_FOUND, logger::error));
        Payment payment = paymentRepository.findByOrder(order)
                .orElseThrow(() -> new BuytopiaException(DATA_NOT_FOUND, logger::error));
        Cart cart = cartRepository.findByMemberId(order.getMember().getId())
                .orElseThrow(() -> new BuytopiaException(DATA_NOT_FOUND, logger::error));
        if (fromCode(request.resultCode())) {
            cart.getItems()
                    .forEach(cartItem -> {
                        Product product = cartItem.getProduct();
                        Long requestedQuantity = cartItem.getQuantity();

                        if (product.getQuantity() < requestedQuantity) {
                            throw new BuytopiaException(PRODUCT_OUT_OF_STOCK, logger::error);
                        }

                        // 재고 감소
                        product.reduceStock(requestedQuantity);
                    });

            payment.completePayment();
            order.markAsOrdered();  // 상태 변경을 위한 메서드 사용
            order.getDelivery().startDelivery();  // 결제 완료되면 배달 시작
            cart.getItems().clear();  // 장바구니에서 모든 항목 제거
        } else {
            payment.failPayment();
        }
        orderRepository.save(order);
        paymentRepository.save(payment);
        cartRepository.save(cart);  // 장바구니 상태 업데이트
    }
}