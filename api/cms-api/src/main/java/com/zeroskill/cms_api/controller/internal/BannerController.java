package com.zeroskill.cms_api.controller.internal;

import com.zeroskill.common.dto.BannerDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Banner;
import com.zeroskill.common.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/banners")
@RequiredArgsConstructor
public class BannerController {

    private static final Logger logger = LogManager.getLogger(BannerController.class);

    private final BannerService bannerService;

    @GetMapping
    public ApiResponse<List<BannerDto>> getAllBanners() {
        List<BannerDto> bannerDtos = bannerService.findAll()
                .stream()
                .map(Banner::of)
                .toList();
        return new ApiResponse<>(null, null, bannerDtos);
    }
}
