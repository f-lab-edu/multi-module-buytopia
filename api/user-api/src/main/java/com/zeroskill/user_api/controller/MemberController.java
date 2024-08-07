package com.zeroskill.user_api.controller;

import com.zeroskill.common.dto.MemberDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.user_api.dto.request.MemberAvailabilityCheckRequest;
import com.zeroskill.user_api.dto.request.MemberRegistrationRequest;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.user_api.service.EmailService;
import com.zeroskill.user_api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import static com.zeroskill.common.util.Util.isValidEmail;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private static final Logger logger = LogManager.getLogger(MemberController.class);

    private final MemberService memberService;

    @GetMapping("/{loginId}")
    public ApiResponse<MemberDto> getMember(@PathVariable("loginId") String loginId) {
        MemberDto memberDto = memberService.getMember(loginId);
        return new ApiResponse<>(null, null, memberDto);
    }

    @PostMapping({"", "/"})
    public void register(@RequestBody MemberRegistrationRequest request) {
        request.check();
        MemberDto memberDto = MemberRegistrationRequest.toMemberDto(request);
        memberService.register(memberDto);
    }

    @PostMapping("/check/availability")
    public void checkMemberAvailability(@RequestBody MemberAvailabilityCheckRequest request) {
        request.check();
        String loginId = request.loginId();
        String email = request.email();
        boolean isDuplicate = memberService.isLoginIdOrEmailDuplicate(loginId, email);
        if (isDuplicate) {
            throw new BuytopiaException(ErrorType.DUPLICATE_ENTITY, logger::error);
        }
    }
}
