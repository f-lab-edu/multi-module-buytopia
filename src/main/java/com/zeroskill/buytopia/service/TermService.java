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

    public List<TermDto> getTermsByIds(List<Long> termIds) {
        List<Term> terms = termRepository.findAllById(termIds);
        return terms.stream()
                .map(TermDto::of)
                .toList();
    }

    public List<AgreementDto> agree(String loginId, List<Long> termIds) {
        // TODO: 필수약관 들어갔는지 체크(required)
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(ErrorType.DATA_NOT_FOUND::exception);
        List<Term> terms = termRepository.findAllById(termIds);
        List<Agreement> agreements = terms.stream()
                .map(term -> new com.zeroskill.buytopia.entity.Agreement(member, term))
                .toList();

        List<Agreement> savedAgreements = agreementRepository.saveAll(agreements);
        return savedAgreements.stream()
                .map(AgreementDto::of)
                .toList();
    }
}
