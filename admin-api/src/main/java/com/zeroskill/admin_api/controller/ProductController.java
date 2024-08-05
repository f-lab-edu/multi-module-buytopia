package com.zeroskill.admin_api.controller;

import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Product;
import com.zeroskill.common.repository.AdminRepository;
import com.zeroskill.common.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    private final AdminRepository adminRepository;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // Admin 권한 확인
        Admin admin = adminRepository.findById(product.getCreatedBy().getId()).orElse(null);
        if (admin == null) {
            return ResponseEntity.status(403).body(null);
        }

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }
}
