package com.zeroskill.admin_api.service;

import com.zeroskill.common.dto.ProductDto;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Category;
import com.zeroskill.common.entity.Discount;
import com.zeroskill.common.entity.Product;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.repository.AdminRepository;
import com.zeroskill.common.repository.CategoryRepository;
import com.zeroskill.common.repository.DiscountRepository;
import com.zeroskill.common.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final Logger logger = LogManager.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;

    public void register(ProductDto dto) {

        Category category = categoryRepository.findById(dto.categoryId()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        Discount discount = discountRepository.findById(dto.discountId()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        Admin creator = adminRepository.findById(dto.createdBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        Admin updater = adminRepository.findById(dto.updatedBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));

        Product product = new Product(dto, category, discount, creator, updater);

        productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
