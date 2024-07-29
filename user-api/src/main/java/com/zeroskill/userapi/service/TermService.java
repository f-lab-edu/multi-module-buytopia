package com.zeroskill.userapi.service;

import com.zeroskill.userapi.dto.AgreementDto;
import com.zeroskill.userapi.dto.TermDto;
import com.zeroskill.userapi.dto.request.PurposeRequest;
import com.zeroskill.userapi.entity.Agreement;
import com.zeroskill.userapi.entity.Member;
import com.zeroskill.userapi.entity.Term;
import com.zeroskill.userapi.entity.TermId;
import com.zeroskill.userapi.exception.UserApiException;
import com.zeroskill.userapi.exception.ErrorType;
import com.zeroskill.userapi.repository.AgreementRepository;
import com.zeroskill.userapi.repository.MemberRepository;
import com.zeroskill.userapi.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

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
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new UserApiException(ErrorType.DATA_NOT_FOUND, logger::error)
        );

        Set<Term> terms = termRepository.findByTermIds(termIds);
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

    private void containsRequiredTerms(PurposeRequest purpose, Set<Term> terms) {
        List<Term> requiredTerms = termRepository.findLatestActiveRequiredTermsByPurpose(purpose.name());

        if(!terms.containsAll(requiredTerms)) {
            throw new UserApiException(ErrorType.MISSING_REQUIRED_TERMS, logger::error);
        }
    }

    public void checkIfUserAlreadyAgreed(Member member, Set<Term> terms) {
        List<Agreement> existingAgreements = agreementRepository.findByMemberAndTermIn(member, terms);
        if (!existingAgreements.isEmpty()) {
            throw new UserApiException(ErrorType.DUPLICATE_ENTITY, logger::error);
        }
    }
}
