package com.zeroskill.buytopia.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeroskill.buytopia.entity.QPurpose;
import com.zeroskill.buytopia.entity.QTerm;
import com.zeroskill.buytopia.entity.QTermPurpose;
import com.zeroskill.buytopia.entity.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TermRepositoryImpl implements TermRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

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
                .orderBy(term.title.asc(), term.version.desc())
                .fetch();

        // 최신 버전만 필터링
        Map<String, Term> latestTermsMap = latestTerms.stream()
                .collect(Collectors.toMap(
                        Term::getTitle,
                        qTerm -> qTerm,
                        (existing, replacement) -> existing // 최신 버전만 유지
                ));

        return new ArrayList<>(latestTermsMap.values());
    }
}
