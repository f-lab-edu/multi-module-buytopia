package com.zeroskill.buytopia.controller;

import com.zeroskill.buytopia.dto.MemberDto;
import com.zeroskill.buytopia.dto.request.MemberAvailabilityCheckRequest;
import com.zeroskill.buytopia.dto.request.MemberRegistrationRequest;
import com.zeroskill.buytopia.dto.response.*;
import com.zeroskill.buytopia.exception.BuytopiaException;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.service.EmailService;
import com.zeroskill.buytopia.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private static final Logger logger = LogManager.getLogger(MemberController.class);

    private final MemberService memberService;
    private final EmailService emailService;

    @PostMapping({"", "/"})
    public ApiResponse<MemberRegistrationResponse> register(@RequestBody MemberRegistrationRequest request) {
        // TODO: app에서 넘어온 약관들이 필수 약관 모두 다 동의했는지 (복합키 비교)
        // TODO: 전화번호, 이메일 인증은 완료시에 유저가 전화번호 인증이 된 사람인지에 대한 식별키를 가지고 있어야 함
        request.check();
        MemberDto memberDto = MemberRegistrationRequest.toMemberDto(request);
        MemberRegistrationResponse memberRegistrationResponse = memberService.register(memberDto);
        return ApiResponse.of(memberRegistrationResponse);
    }

    @PostMapping("/check/availability")
    public ApiResponse<MemberAvailabilityCheckResponse> checkMemberAvailability(@RequestBody MemberAvailabilityCheckRequest request) {
        request.check();
        String loginId = request.loginId();
        String email = request.email();
        boolean isDuplicate = memberService.isLoginIdOrEmailDuplicate(loginId, email);
        if (isDuplicate) {
            throw new BuytopiaException(ErrorType.DUPLICATE_ENTITY, logger::error);
        }
        return ApiResponse.of(new MemberAvailabilityCheckResponse(true));
    }

    @PostMapping("/send/verification-email")
    public ApiResponse<SednVerificationEmailResponse> sendVerificationEmail(@RequestParam("email") String email) {
        emailService.sendVerificationEmail(email);
        return ApiResponse.of(new SednVerificationEmailResponse("정상적으로 메일이 발송되었습니다."));
    }

    @GetMapping("/verify/email")
    public ApiResponse<VerifyEmailResponse> verifyEmail(@RequestParam("token") String token) {
        boolean verified = emailService.verifyEmail(token);
        if (verified) {
            return ApiResponse.of(new VerifyEmailResponse("이메일 인증이 성공했습니다."));
        } else {
            throw new BuytopiaException(ErrorType.AUTHENTICATION_FAILED, logger::error);
        }
    }
}
