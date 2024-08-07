package com.zeroskill.web_bff.controller;

import com.zeroskill.common.dto.MemberDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.web_bff.client.UserApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final UserApiClient userApiClient;

    @GetMapping("/main-page")
    public ApiResponse<MemberDto> getMainPageData() {
        return userApiClient.getMemberByLoginId("zeroskill2400");  // 예제 ID
    }
}