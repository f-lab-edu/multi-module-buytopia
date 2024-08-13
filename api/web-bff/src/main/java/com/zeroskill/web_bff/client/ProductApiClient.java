package com.zeroskill.web_bff.client;

import com.zeroskill.common.dto.CategoryDto;
import com.zeroskill.common.dto.DiscountDto;
import com.zeroskill.common.dto.DiscountedProductDto;
import com.zeroskill.common.dto.ProductDto;
import com.zeroskill.common.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "product-api", url = "http://localhost:18083/internal/api/v1/")
public interface ProductApiClient {

    @GetMapping("/products")
    ApiResponse<ProductDto> getAllProducts();

    @GetMapping("/products/discounted")
    ApiResponse<List<DiscountedProductDto>> getAllDiscountedProduct(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @GetMapping("/categories")
    ApiResponse<CategoryDto> getAllCategories();

    @GetMapping("/discounts")
    ApiResponse<DiscountDto> getAllDiscounts();
}