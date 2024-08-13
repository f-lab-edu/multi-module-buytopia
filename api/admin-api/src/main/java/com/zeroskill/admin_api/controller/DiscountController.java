package com.zeroskill.admin_api.controller;

import com.zeroskill.admin_api.dto.request.DiscountRegistrationRequest;
import com.zeroskill.common.service.AdminService;
import com.zeroskill.common.service.DiscountService;
import com.zeroskill.common.dto.DiscountDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Discount;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private static final Logger logger = LogManager.getLogger(DiscountController.class);

    private final DiscountService discountService;
    private final AdminService adminService;

    @PostMapping
    public void addDiscount(@RequestBody DiscountRegistrationRequest request) {
        request.check();
        Admin admin = adminService.findById(request.createdBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        if (admin == null) {
            throw new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error);
        }
        DiscountDto discountDto = DiscountRegistrationRequest.toDiscountDto(request);
        discountService.register(discountDto);
    }

    @GetMapping
    public ApiResponse<List<DiscountDto>> getAllDiscounts() {
        List<DiscountDto> discountDtos = discountService.findAll()
                .stream()
                .map(Discount::of)
                .toList();
        return new ApiResponse<>(null, null, discountDtos);
    }
}
