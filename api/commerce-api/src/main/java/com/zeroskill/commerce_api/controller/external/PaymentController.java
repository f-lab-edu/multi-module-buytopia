package com.zeroskill.commerce_api.controller.external;

import com.zeroskill.common.dto.request.PaymentResultRequest;
import com.zeroskill.common.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/callback")
    public void handlePaymentCallback(@RequestBody PaymentResultRequest request) {
        paymentService.handlePaymentCallback(request);
    }
}
