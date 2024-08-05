package com.zeroskill.admin_api.controller;

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
    public ResponseEntity<Discount> addDiscount(@RequestBody Discount discount) {
        Discount savedDiscount = discountRepository.save(discount);
        return ResponseEntity.ok(savedDiscount);
    }

    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        return ResponseEntity.ok(discounts);
    }
}
