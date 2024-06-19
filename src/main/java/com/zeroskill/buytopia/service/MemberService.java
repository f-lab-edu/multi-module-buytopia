package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.dto.MemberDto;
import com.zeroskill.buytopia.dto.response.MemberRegistrationResponse;
import com.zeroskill.buytopia.entity.Address;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.exception.DuplicateEntityException;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.zeroskill.buytopia.dto.MemberDto.hashPassword;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public MemberRegistrationResponse register(MemberDto memberDto) {
        if(isLoginIdOrEmailDuplicate(memberDto.loginId(), memberDto.email())) {
            throw new DuplicateEntityException(ErrorType.DUPLICATE_ENTITY_CD.getData());
        }
        String hashedPassword = passwordEncoder.encode(memberDto.password());
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
}

