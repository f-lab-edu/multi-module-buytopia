package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.dto.MemberDto;
import com.zeroskill.buytopia.dto.response.MemberRegistrationResponse;
import com.zeroskill.buytopia.entity.Address;
import com.zeroskill.buytopia.entity.Member;
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
            // TODO: new BuytopiaException 생성자로 생성하고 LogLevel또한 파라미터로 넣어주기
            // consumer(lambda로 log.info라고 하는 메서드)를 넘기면 됨
            // 세부사항들은 개발자가 볼 수 있게 로그를 남겨야 함.(넘어온 log.info의 클래스나 부족한 데이터 등을 찍어줘야함.)
            throw ErrorType.DUPLICATE_ENTITY.exception();
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

