package com.zeroskill.user_api.service;

import com.zeroskill.common.dto.MemberDto;
import com.zeroskill.user_api.dto.response.MemberRegistrationResponse;
import com.zeroskill.common.entity.Address;
import com.zeroskill.common.entity.Member;
import com.zeroskill.user_api.exception.UserApiException;
import com.zeroskill.user_api.exception.ErrorType;
import com.zeroskill.common.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.zeroskill.common.dto.MemberDto.hashPassword;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(MemberService.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final MemberRepository memberRepository;

    public MemberRegistrationResponse register(MemberDto memberDto) {
        if (isLoginIdOrEmailDuplicate(memberDto.loginId(), memberDto.email())) {
            throw new UserApiException(ErrorType.DUPLICATE_ENTITY, logger::error);
        }
        String hashedPassword = passwordEncoder().encode(memberDto.password());
        Address address = Address.toEntity(memberDto.addressdto());
        Member member = Member.toEntity(hashPassword(memberDto, hashedPassword), address);
        Member savedMember = memberRepository.save(member);
        return new MemberRegistrationResponse(savedMember);
    }

    public boolean isLoginIdDuplicate(String loginId) {
        return memberRepository.findByLoginId(loginId).isPresent();
    }

    public boolean isEmailDuplicate(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public boolean isLoginIdOrEmailDuplicate(String loginId, String email) {
        return isLoginIdDuplicate(loginId) || isEmailDuplicate(email);
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UserApiException(ErrorType.DATA_NOT_FOUND, logger::error));
        if (member == null) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        }
        return new User(member.getLoginId(), member.getPassword(), new ArrayList<>());
    }
}