package com.zeroskill.admin_api.controller;

import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Product;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.repository.AdminRepository;
import com.zeroskill.common.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        // Admin 권한 확인
        Admin admin = adminRepository.findById(product.getCreatedBy().getId()).orElse(null);
        if (admin == null) {
            throw new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error);
        }

        productRepository.save(product);
    }

    @GetMapping
    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ApiResponse<>(null, null, products);
    }
}
