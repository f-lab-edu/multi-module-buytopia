package com.zeroskill.common.repository;

import com.zeroskill.common.entity.Product;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findAllDiscountedProduct(LocalDate currentDate);
}