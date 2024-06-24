package com.zeroskill.buytopia.controller;

import com.zeroskill.buytopia.dto.MemberTermDto;
import com.zeroskill.buytopia.dto.TermDto;
import com.zeroskill.buytopia.dto.request.AgreeToTermsRequest;
import com.zeroskill.buytopia.dto.request.GetTermsByTermIdsRequest;
import com.zeroskill.buytopia.dto.response.ApiResponse;
import com.zeroskill.buytopia.service.TermService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/terms")
@RequiredArgsConstructor
public class TermController {

    private final TermService termService;

    @GetMapping("/")
    public ApiResponse<List<TermDto>> getTermsByIds(@RequestBody GetTermsByTermIdsRequest request) {
        request.checkEmptyField();
        List<TermDto> termDtos = termService.getTermsByIds(request.termIds());
        return ApiResponse.of(termDtos);
    }

    @PostMapping("/agree")
    public ApiResponse<List<MemberTermDto>> agreeToTerms(@RequestBody AgreeToTermsRequest request) {
        request.checkEmptyField();
        List<MemberTermDto> memberTermDtos = termService.agree(request.loginId(), request.termIds());
        return ApiResponse.of(memberTermDtos);
    }
}
