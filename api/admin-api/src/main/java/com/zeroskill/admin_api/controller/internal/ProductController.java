package com.zeroskill.admin_api.controller.internal;

import com.zeroskill.admin_api.dto.request.ProductRegistrationRequest;
import com.zeroskill.common.service.AdminService;
import com.zeroskill.common.service.ProductService;
import com.zeroskill.common.dto.ProductDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Product;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService productService;
    private final AdminService adminService;

    @PostMapping
    public void addProduct(@RequestBody ProductRegistrationRequest request) {
        request.check();
        // Admin 권한 확인
        Admin admin = adminService.findById(request.createdBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        if (admin == null) {
            throw new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error);
        }

        ProductDto dto = ProductRegistrationRequest.toProductDto(request);
        productService.register(dto);
    }

    @GetMapping
    public ApiResponse<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.findAll()
                .stream()
                .map(Product::of)
                .toList();
        return new ApiResponse<>(null, null, productDtos);
    }
}
