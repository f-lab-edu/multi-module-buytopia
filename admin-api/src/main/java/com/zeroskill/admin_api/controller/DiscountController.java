package com.zeroskill.admin_api.controller;

import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Discount;
import com.zeroskill.common.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountRepository discountRepository;

    @PostMapping
    public void addDiscount(@RequestBody Discount discount) {
        discountRepository.save(discount);
    }

    @GetMapping
    public ApiResponse<List<Discount>> getAllDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        return new ApiResponse<>(null, null, discounts);
    }
}
