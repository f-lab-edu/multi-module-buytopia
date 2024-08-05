package com.zeroskill.user_api.filter;

import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.user_api.service.VerificationTokenService;
import com.zeroskill.user_api.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger(JwtRequestFilter.class);

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final VerificationTokenService verificationTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String loginId = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            loginId = jwtUtil.getLoginIdFromToken(jwt);
        }

        if (loginId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (verificationTokenService.isTokenNotInWhiteList(jwt)) {
                throw new BuytopiaException(ErrorType.AUTHENTICATION_FAILED, logger::error);
            }

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginId);

            if (jwtUtil.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}