package com.zeroskill.product_api.controller.internal;

import com.zeroskill.common.dto.DiscountedProductDto;
import com.zeroskill.common.dto.ProductDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Product;
import com.zeroskill.common.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping
    public ApiResponse<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.findAll()
                .stream()
                .map(Product::of)
                .toList();
        return new ApiResponse<>(null, null, productDtos);
    }

    @GetMapping("/discounted")
    public ApiResponse<List<DiscountedProductDto>> getAllDiscountedProduct(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ApiResponse<>(null, null, productService.getAllDiscountedProduct(date));
    }
}
