package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.dto.MemberDto;
import com.zeroskill.buytopia.dto.response.MemberRegistrationResponse;
import com.zeroskill.buytopia.entity.Address;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.exception.BuytopiaException;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.filter.JwtRequestFilter;
import com.zeroskill.buytopia.repository.MemberRepository;
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

import static com.zeroskill.buytopia.dto.MemberDto.hashPassword;

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
            throw new BuytopiaException(ErrorType.DUPLICATE_ENTITY, logger::error);
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
                .orElseThrow(() -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error));
        if (member == null) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        }
        return new User(member.getLoginId(), member.getPassword(), new ArrayList<>());
    }
}