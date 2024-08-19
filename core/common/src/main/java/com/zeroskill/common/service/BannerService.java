package com.zeroskill.common.service;

import com.zeroskill.common.dto.BannerDto;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Banner;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.repository.AdminRepository;
import com.zeroskill.common.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerService {

    private static final Logger logger = LogManager.getLogger(BannerService.class);

    private final BannerRepository bannerRepository;
    private final AdminRepository adminRepository;

    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    public void register(BannerDto dto) {
        Admin creator = adminRepository.findById(dto.createdBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        Admin updater = adminRepository.findById(dto.updatedBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));

        Banner banner = Banner.toEntity(dto, creator, updater);
        bannerRepository.save(banner);
    }
}
