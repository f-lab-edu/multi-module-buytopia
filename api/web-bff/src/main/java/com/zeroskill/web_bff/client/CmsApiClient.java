package com.zeroskill.web_bff.client;

import com.zeroskill.common.dto.BannerDto;
import com.zeroskill.common.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-api", url = "http://localhost:18084/internal/api/v1/")
public interface CmsApiClient {

    @GetMapping("/banners")
    ApiResponse<List<BannerDto>> getAllBanners();
}
