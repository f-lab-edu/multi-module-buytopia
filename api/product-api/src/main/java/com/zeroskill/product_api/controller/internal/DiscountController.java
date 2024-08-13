package com.zeroskill.product_api.controller.internal;

import com.zeroskill.common.dto.DiscountDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Discount;
import com.zeroskill.common.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private static final Logger logger = LogManager.getLogger(DiscountController.class);

    private final DiscountService discountService;

    @GetMapping
    public ApiResponse<List<DiscountDto>> getAllDiscounts() {
        List<DiscountDto> discountDtos = discountService.findAll()
                .stream()
                .map(Discount::of)
                .toList();
        return new ApiResponse<>(null, null, discountDtos);
    }
}
