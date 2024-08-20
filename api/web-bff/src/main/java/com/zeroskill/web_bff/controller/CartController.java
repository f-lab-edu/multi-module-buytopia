package com.zeroskill.web_bff.controller;

import com.zeroskill.web_bff.client.CommerceApiClient;
import com.zeroskill.web_bff.dto.request.AddProductToCartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CommerceApiClient commerceApiClient;

    @PostMapping("/carts")
    public void addProductToCart(@RequestParam Long memberId, @RequestBody AddProductToCartRequest products) {
        commerceApiClient.addProductToCart(memberId, products);
    }
}
