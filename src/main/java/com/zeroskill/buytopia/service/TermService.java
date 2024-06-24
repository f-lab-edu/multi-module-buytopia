package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.dto.MemberTermDto;
import com.zeroskill.buytopia.dto.TermDto;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.entity.MemberTerm;
import com.zeroskill.buytopia.entity.Term;
import com.zeroskill.buytopia.exception.DataNotFoundException;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.repository.MemberRepository;
import com.zeroskill.buytopia.repository.MemberTermRepository;
import com.zeroskill.buytopia.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {
    private final MemberRepository memberRepository;
    private final TermRepository termRepository;
    private final MemberTermRepository memberTermRepository;

    public List<TermDto> getTermsByIds(List<Long> termIds) {
        List<Term> terms = termRepository.findAllById(termIds);
        return terms.stream()
                .map(TermDto::of)
                .toList();
    }

    public List<MemberTermDto> agree(String loginId, List<Long> termIds) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new DataNotFoundException(ErrorType.DATA_NOT_FOUND_CD.getData()));
        List<Term> terms = termRepository.findAllById(termIds);
        List<MemberTerm> memberTerms = terms.stream()
                .map(term -> new MemberTerm(member, term))
                .toList();

        List<MemberTerm> savedMemberTerms = memberTermRepository.saveAll(memberTerms);
        return savedMemberTerms.stream()
                .map(MemberTermDto::of)
                .toList();
    }
}
