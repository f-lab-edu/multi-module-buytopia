package com.zeroskill.web_bff.client;

import com.zeroskill.web_bff.dto.request.AddProductToCartRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "commerce-api", url = "http://localhost:18086/internal/api/v1/")
public interface CommerceApiClient {

    @PostMapping("/carts")
    void addProductToCart(@RequestParam Long memberId, @RequestBody AddProductToCartRequest request);
}
