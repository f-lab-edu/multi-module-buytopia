package com.zeroskill.buytopia.controller;

import com.zeroskill.buytopia.dto.MemberDto;
import com.zeroskill.buytopia.dto.request.MemberAvailabilityCheckRequest;
import com.zeroskill.buytopia.dto.request.MemberRegistrationRequest;
import com.zeroskill.buytopia.dto.response.ApiResponse;
import com.zeroskill.buytopia.dto.response.MemberAvailabilityCheckResponse;
import com.zeroskill.buytopia.dto.response.MemberRegistrationResponse;
import com.zeroskill.buytopia.exception.BuytopiaException;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping({"", "/"})
    public ApiResponse<MemberRegistrationResponse> register(@RequestBody MemberRegistrationRequest request) {
        request.checkEmptyField();
        request.checkPasswordMatch();
        MemberDto memberDto = MemberRegistrationRequest.toMemberDto(request);
        MemberRegistrationResponse memberRegistrationResponse = memberService.register(memberDto);
        return ApiResponse.of(memberRegistrationResponse);
    }

    @PostMapping("/check/availability")
    public ApiResponse<MemberAvailabilityCheckResponse> checkMemberAvailability(@RequestBody MemberAvailabilityCheckRequest request) {
        request.checkEmptyField();
        String loginId = request.loginId();
        String email = request.email();
        boolean isDuplicate = memberService.isLoginIdOrEmailDuplicate(loginId, email);
        if (isDuplicate) {
            throw ErrorType.DUPLICATE_ENTITY.exception();
        }
        return ApiResponse.of(new MemberAvailabilityCheckResponse(true));
    }
}
