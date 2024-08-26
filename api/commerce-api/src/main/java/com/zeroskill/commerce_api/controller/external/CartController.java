package com.zeroskill.commerce_api.controller.external;

import com.zeroskill.commerce_api.dto.request.AddProductToCartRequest;
import com.zeroskill.common.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public void addProductToCart(@RequestParam Long memberId, @RequestBody AddProductToCartRequest request) {
        cartService.addProductToCart(memberId, request.products());
    }
}
