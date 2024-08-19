package com.zeroskill.admin_api.controller.internal;

import com.zeroskill.admin_api.dto.request.BannerRegistrationRequest;
import com.zeroskill.common.dto.BannerDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.entity.Banner;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.service.AdminService;
import com.zeroskill.common.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/banners")
@RequiredArgsConstructor
public class BannerController {

    private static final Logger logger = LogManager.getLogger(BannerController.class);

    private final AdminService adminService;
    private final BannerService bannerService;

    @PostMapping
    public void addBanner(@RequestBody BannerRegistrationRequest request) {
        request.check();
        Admin admin = adminService.findById(request.createdBy()).orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        if (admin == null) {
            throw new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error);
        }

        BannerDto bannerDto = BannerRegistrationRequest.toBannerDto(request);
        bannerService.register(bannerDto);
    }

    @GetMapping
    public ApiResponse<List<BannerDto>> getAllBanners() {
        List<BannerDto> bannerDtos = bannerService.findAll()
                .stream()
                .map(Banner::of)
                .toList();
        return new ApiResponse<>(null, null, bannerDtos);
    }
}
