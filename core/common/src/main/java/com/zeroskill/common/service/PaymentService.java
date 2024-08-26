package com.zeroskill.common.service;

import com.zeroskill.common.dto.PaymentDto;
import com.zeroskill.common.entity.Payment;
import com.zeroskill.common.entity.PaymentMethod;
import com.zeroskill.common.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentDto createPayment(PaymentDto dto) {
        Payment payment = Payment.createPayment(
                dto.paymentMethod(),
                dto.amount()
        );
        Payment savedPayment = paymentRepository.save(payment);
        return PaymentDto.of(savedPayment);
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
    public PaymentDto processPayment(PaymentMethod paymentMethod, Long amount) {
        // PENDING 상태로 결제 생성
        Payment payment = Payment.createPayment(paymentMethod, amount);
        Payment savedPayment = paymentRepository.save(payment);

        // 실제 결제 처리 로직을 여기에 추가 (예: 외부 결제 게이트웨이 통신)
        // 예시로 결제가 항상 성공하는 시나리오
        savedPayment.completePayment();  // 결제 성공으로 상태 변경

        return PaymentDto.of(savedPayment);  // PaymentDto로 변환
    }
}