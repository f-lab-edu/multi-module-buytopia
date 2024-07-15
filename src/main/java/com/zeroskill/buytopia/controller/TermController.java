package com.zeroskill.buytopia.controller;

import com.zeroskill.buytopia.dto.AgreementDto;
import com.zeroskill.buytopia.dto.TermDto;
import com.zeroskill.buytopia.dto.request.AgreeToTermsRequest;
import com.zeroskill.buytopia.dto.request.PurposeRequest;
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

    @GetMapping("")
    public ApiResponse<List<TermDto>> getTermsByIds(@RequestParam("purpose") PurposeRequest purpose) {
        List<TermDto> termDtos = termService.getTermsByPurpose(purpose);
        return ApiResponse.of(termDtos);
    }

    @PostMapping("/agree")
    public void agreeToTerms(@RequestParam("purpose") PurposeRequest purpose, @RequestBody AgreeToTermsRequest request) {
        request.check();
        termService.agree(purpose, request.loginId(), request.termIds());
    }
}
