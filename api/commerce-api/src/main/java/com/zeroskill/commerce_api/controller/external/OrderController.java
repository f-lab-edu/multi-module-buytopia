package com.zeroskill.commerce_api.controller.external;

import com.zeroskill.common.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestParam Long memberId) {
        orderService.createOrderFromCart(memberId, "김범수", "01086282287");
    }
}
