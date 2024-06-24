package com.zeroskill.buytopia.controller;

import com.zeroskill.buytopia.dto.TermDto;
import com.zeroskill.buytopia.dto.request.GetTermsByTermIdsRequest;
import com.zeroskill.buytopia.dto.response.ApiResponse;
import com.zeroskill.buytopia.service.TermService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
