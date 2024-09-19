package com.zeroskill.common.repository;

import com.zeroskill.common.entity.Order;
import com.zeroskill.common.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrder(Order order);
}
