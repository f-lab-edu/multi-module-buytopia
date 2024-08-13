package com.zeroskill.web_bff.client;

import com.zeroskill.common.dto.MemberDto;
import com.zeroskill.common.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-api", url = "http://localhost:18082/internal/api/v1")
public interface UserApiClient {

    @GetMapping("/members/{loginId}")
    ApiResponse<MemberDto> getMemberByLoginId(@PathVariable("loginId") String loginId);
}