package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.dto.AgreementDto;
import com.zeroskill.buytopia.dto.TermDto;
import com.zeroskill.buytopia.dto.request.PurposeRequest;
import com.zeroskill.buytopia.entity.Agreement;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.entity.Term;
import com.zeroskill.buytopia.entity.TermId;
import com.zeroskill.buytopia.exception.BuytopiaException;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.repository.AgreementRepository;
import com.zeroskill.buytopia.repository.MemberRepository;
import com.zeroskill.buytopia.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TermService {
    private static final Logger logger = LogManager.getLogger(TermService.class);

    private final MemberRepository memberRepository;
    private final TermRepository termRepository;
    private final AgreementRepository agreementRepository;

    public List<TermDto> getTermsByPurpose(PurposeRequest purpose) {
        List<Term> terms = termRepository.findLatestActiveTermsByPurpose(purpose.name());
        return terms.stream()
                .map(TermDto::of)
                .toList();
    }

    public List<AgreementDto> agree(PurposeRequest purpose, String loginId, List<TermId> termIds) {
        // TODO: 필수약관 들어갔는지 체크(required)
        // Member 조회
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error)
        );

        List<Term> terms = termRepository.findByTermIds(termIds);
        checkIfUserAlreadyAgreed(member, terms);
        containsRequiredTerms(purpose, terms);

        List<Agreement> agreements = terms.stream()
                .map(term -> new Agreement(member, term))
                .toList();

        List<Agreement> savedAgreements = agreementRepository.bulkInsert(agreements);
        return savedAgreements.stream()
                .map(AgreementDto::of)
                .toList();
    }

    private void containsRequiredTerms(PurposeRequest purpose, List<Term> terms) {
        Set<Term> termSet = new HashSet<>(terms);
        List<Term> requiredTerms = termRepository.findLatestActiveRequiredTermsByPurpose(purpose.name());

        if(!termSet.containsAll(requiredTerms)) {
            throw new BuytopiaException(ErrorType.MISSING_REQUIRED_TERMS, logger::error);
        }
    }

    public void checkIfUserAlreadyAgreed(Member member, List<Term> terms) {
        List<Agreement> existingAgreements = agreementRepository.findByMemberAndTermIn(member, terms);
        if (!existingAgreements.isEmpty()) {
            throw new BuytopiaException(ErrorType.DUPLICATE_ENTITY, logger::error);
        }
    }

}
