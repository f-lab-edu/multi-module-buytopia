package com.zeroskill.web_bff.controller;

import com.zeroskill.common.dto.BannerDto;
import com.zeroskill.common.dto.DiscountedProductDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.web_bff.client.CmsApiClient;
import com.zeroskill.web_bff.client.ProductApiClient;
import com.zeroskill.web_bff.client.UserApiClient;
import com.zeroskill.web_bff.dto.MainPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final UserApiClient userApiClient;
    private final ProductApiClient productApiClient;
    private final CmsApiClient cmsApiClient;

    /**
     * {
     *   startIndex: "",
     *   bannerList: [{ // 첫주문인지, 주소가 어딘지(geopartial db에서 캐싱), 나이대, 성별 등등을 가지고 배너를 노출하는게 다름
     *       bannerId: "",
     *       bannerLink: "",
     *       imgUrl: "",
     *       interval: ""
     *     }, ...
     *   ],
     *   saledProductTitle: "",
     *   saledProductDescription: "", // 실제로는 font, emoji등들도 같이 내려줘야함
     *   saledProductList: [{
     *       productid: "",
     *       imgUrl: "",
     *       badge: "",
     *       name: "",
     *       description: "",
     *       price: "",
     *       discountRate: "",
     *       discountedPrice: "",
     *       replyNumber: ""
     *     }
     *   ],
     *   subBanner: {
     *   },
     *   timeDeal: {
     *   } // 데몬 활용 bff
     * }
     */
    @GetMapping("/main-page")
    public ApiResponse<MainPageResponse> getMainPageData(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        ApiResponse<List<BannerDto>> bannerResponse = cmsApiClient.getAllBanners();
        ApiResponse<List<DiscountedProductDto>> discountResponse = productApiClient.getAllDiscountedProduct(LocalDate.from(date));
        return new ApiResponse<>(null, null, new MainPageResponse(bannerResponse.body(), discountResponse.body()));
    }
}