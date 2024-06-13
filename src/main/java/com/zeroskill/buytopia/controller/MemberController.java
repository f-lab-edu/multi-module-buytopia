package com.zeroskill.buytopia.controller;

import com.zeroskill.buytopia.dto.MemberDto;
import com.zeroskill.buytopia.dto.request.MemberAvailabilityCheckRequest;
import com.zeroskill.buytopia.dto.request.MemberRegistrationRequest;
import com.zeroskill.buytopia.dto.response.MemberAvailabilityCheckResponse;
import com.zeroskill.buytopia.dto.response.MemberRegistrationResponse;
import com.zeroskill.buytopia.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zeroskill.buytopia.converter.ResponseConverter.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping({"", "/"})
    public ResponseEntity<MemberRegistrationResponse> register(@RequestBody MemberRegistrationRequest request) {
        request.checkEmptyField();
        request.checkPasswordMatch();
        MemberDto memberDto = MemberRegistrationRequest.toMemberDto(request);
        MemberRegistrationResponse memberRegistrationResponse = memberService.register(memberDto);
        return convertToSucessResponseEntity(memberRegistrationResponse);
    }

    @PostMapping("/check/availability")
    public ResponseEntity<MemberAvailabilityCheckResponse> checkMemberAvailability(@RequestBody MemberAvailabilityCheckRequest request) {
        request.checkEmptyField();
        String loginId = request.loginId();
        String email = request.email();
        boolean isDuplicate = memberService.isLoginIdOrEmailDuplicate(loginId, email);
        if (isDuplicate) {
            return convertToBadRequest(new MemberAvailabilityCheckResponse(false, "LoginId or email is already taken"));
        }
        return convertToSucessResponseEntity(new MemberAvailabilityCheckResponse(true, "LoginId or email are valid"));
    }
}
