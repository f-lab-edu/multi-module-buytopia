package com.zeroskill.user_api.controller;

import com.zeroskill.user_api.dto.request.AuthRequest;
import com.zeroskill.user_api.dto.request.LogoutAuthRequest;
import com.zeroskill.user_api.dto.request.RefreshAuthRequest;
import com.zeroskill.user_api.dto.response.ApiResponse;
import com.zeroskill.user_api.dto.response.AuthResponse;
import com.zeroskill.user_api.exception.ErrorType;
import com.zeroskill.user_api.service.MemberService;
import com.zeroskill.user_api.service.VerificationTokenService;
import com.zeroskill.user_api.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;
    private final VerificationTokenService verificationTokenService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.loginId(), authRequest.password()));

        final UserDetails userDetails = memberService.loadUserByUsername(authRequest.loginId());
        final String accessToken = jwtUtil.generateAccessToken(userDetails.getUsername());
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

        verificationTokenService.saveToken("accessToken:" + userDetails.getUsername(), accessToken, jwtUtil.getAccessExpirationTime(), TimeUnit.MILLISECONDS);
        verificationTokenService.saveToken("refreshToken:" + userDetails.getUsername(), refreshToken, jwtUtil.getRefreshExpirationTime(), TimeUnit.MILLISECONDS);

        return new ApiResponse<>(null, null, new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refreshAccessToken(@RequestBody RefreshAuthRequest request) {
        String refreshToken = request.refreshToken();
        if (jwtUtil.validateToken(refreshToken)) {
            String loginId = jwtUtil.getLoginIdFromToken(refreshToken);
            String storedRefreshToken = verificationTokenService.getToken("refreshToken:" + loginId);

            if (refreshToken.equals(storedRefreshToken)) {
                String newAccessToken = jwtUtil.generateAccessToken(loginId);
                verificationTokenService.saveToken("accessToken:" + loginId, newAccessToken, jwtUtil.getAccessExpirationTime(), TimeUnit.MILLISECONDS);
                return new ApiResponse<>(null, null, new AuthResponse(newAccessToken, refreshToken));
            }
        }
        return new ApiResponse<>(ErrorType.AUTHENTICATION_FAILED.getCode(), ErrorType.AUTHENTICATION_FAILED.getExtMsg(), null);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutAuthRequest request) {
        String accessToken = request.accessToken();
        if (jwtUtil.validateToken(accessToken)) {
            String loginId = jwtUtil.getLoginIdFromToken(accessToken);
            verificationTokenService.deleteToken("accessToken:" + loginId);
            verificationTokenService.deleteToken("refreshToken:" + loginId);
            // 블랙리스트 추가 가능
        }
    }
}
