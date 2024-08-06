package com.zeroskill.admin_api.service;

import com.zeroskill.common.dto.DiscountDto;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Discount;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.repository.AdminRepository;
import com.zeroskill.common.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private static final Logger logger = LogManager.getLogger(DiscountService.class);

    private final AdminRepository adminRepository;
    private final DiscountRepository discountRepository;

    public void register(DiscountDto dto) {
        Admin creator = adminRepository.findById(dto.createdBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        Admin updater = adminRepository.findById(dto.updatedBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        Discount discount = Discount.toEntity(dto, creator, updater);

        discountRepository.save(discount);
    }

    public List<Discount> findAll() {
        return discountRepository.findAll();
    }
}
