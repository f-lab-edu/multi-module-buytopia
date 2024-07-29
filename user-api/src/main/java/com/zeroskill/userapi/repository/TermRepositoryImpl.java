package com.zeroskill.userapi.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeroskill.userapi.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TermRepositoryImpl implements TermRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Term> findLatestActiveTermsByPurpose(String purpose) {
        QTerm term = QTerm.term;
        QTermPurpose termPurpose = QTermPurpose.termPurpose;
        QPurpose purposeEntity = QPurpose.purpose;

        // TODO: 최적의 쿼리 방법 찾아보기
        // data사이즈가 클 경우 join을 이용해 필요한 데이터만 가져온다.
        // 자주 사용하지 않기때문에 join을 사용해도 괜찮다.
        // 서브쿼리로 최신 버전 찾기
        List<Term> latestTerms = queryFactory
                .selectFrom(term)
                .join(term.termPurposes, termPurpose)
                .join(termPurpose.purpose, purposeEntity)
                .where(term.isActive.isTrue()
                        .and(termPurpose.purpose.name.eq(purpose)))
                .orderBy(term.id.title.asc(), term.id.version.desc())
                .fetch();

        // 최신 버전만 필터링
        Map<String, Term> latestTermsMap = latestTerms.stream()
                .collect(Collectors.toMap(
                        qterm -> qterm.getId().getTitle(),
                        qTerm -> qTerm,
                        (existing, replacement) -> existing // 최신 버전만 유지
                ));

        return new ArrayList<>(latestTermsMap.values());
    }

    @Override
    public List<Term> findLatestActiveRequiredTermsByPurpose(String purpose) {
        QTerm term = QTerm.term;
        QTermPurpose termPurpose = QTermPurpose.termPurpose;
        QPurpose purposeEntity = QPurpose.purpose;

        // TODO: 최적의 쿼리 방법 찾아보기
        // data사이즈가 클 경우 join을 이용해 필요한 데이터만 가져온다.
        // 자주 사용하지 않기때문에 join을 사용해도 괜찮다.
        // 서브쿼리로 최신 버전 찾기
        List<Term> latestTerms = queryFactory
                .selectFrom(term)
                .join(term.termPurposes, termPurpose)
                .join(termPurpose.purpose, purposeEntity)
                .where(term.isActive.isTrue()
                        .and(termPurpose.purpose.name.eq(purpose))
                        .and(term.required.isTrue()))
                .orderBy(term.id.title.asc(), term.id.version.desc())
                .fetch();

        // 최신 버전만 필터링
        Map<String, Term> latestTermsMap = latestTerms.stream()
                .collect(Collectors.toMap(
                        qterm -> qterm.getId().getTitle(),
                        qTerm -> qTerm,
                        (existing, replacement) -> existing // 최신 버전만 유지
                ));

        return new ArrayList<>(latestTermsMap.values());
    }

    @Override
    public Set<Term> findByTermIds(List<TermId> termIds) {
        QTerm qTerm = QTerm.term;

        return new HashSet<>(queryFactory.selectFrom(qTerm)
                .where(qTerm.id.in(termIds))
                .fetch());
    }
}
