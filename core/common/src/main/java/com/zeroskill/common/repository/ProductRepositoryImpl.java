package com.zeroskill.common.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeroskill.common.entity.Product;
import com.zeroskill.common.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAllDiscountedProduct(LocalDate currentDate) {
        QProduct product = QProduct.product;

        return queryFactory.selectFrom(product)
                .where(product.discount.isNotNull()
                        .and(product.discount.startDate.loe(currentDate))
                        .and(product.discount.endDate.goe(currentDate))
                        .and(product.quantity.gt(0)))
                .fetch();
    }
}
