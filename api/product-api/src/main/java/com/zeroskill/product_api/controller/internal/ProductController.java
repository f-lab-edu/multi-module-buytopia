package com.zeroskill.product_api.controller.internal;

import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Product;
import com.zeroskill.common.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping
    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return new ApiResponse<>(null, null, products);
    }
}
