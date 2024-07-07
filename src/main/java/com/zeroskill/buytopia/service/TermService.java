package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.dto.AgreementDto;
import com.zeroskill.buytopia.dto.TermDto;
import com.zeroskill.buytopia.entity.Agreement;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.entity.Term;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.repository.MemberRepository;
import com.zeroskill.buytopia.repository.AgreementRepository;
import com.zeroskill.buytopia.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {
    private final MemberRepository memberRepository;
    private final TermRepository termRepository;
    private final AgreementRepository agreementRepository;

    // 로직을 분리하는 방법도 있음
    public List<TermDto> getTermsByPurpose(String purpose) {
        List<Term> terms = termRepository.findLatestActiveTermsByPurpose(purpose);
        return terms.stream()
                .map(TermDto::of)
                .toList();
    }

    public List<AgreementDto> agree(String loginId, List<Long> termIds) {
        // TODO: 필수약관 들어갔는지 체크(required)
        // Member 조회
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(ErrorType.DATA_NOT_FOUND::exception);
        // Term 조회
        List<Term> terms = termRepository.findAllById(termIds);

        // 필수 약관 체크
//        List<Term> requiredTerms = terms.stream()
//                .filter(Term::isRequired)
//                .toList();
//
//        long requiredTermCount = termRepository.countByRequiredTrue();
//        System.out.println("count: " + requiredTermCount);


        List<Agreement> agreements = terms.stream()
                .map(term -> new Agreement(member, term))
                .toList();

        List<Agreement> savedAgreements = agreementRepository.saveAll(agreements);
        return savedAgreements.stream()
                .map(AgreementDto::of)
                .toList();
    }
}
