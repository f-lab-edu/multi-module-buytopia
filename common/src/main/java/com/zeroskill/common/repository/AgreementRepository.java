package com.zeroskill.common.repository;

import com.zeroskill.common.entity.Agreement;
import com.zeroskill.common.entity.Member;
import com.zeroskill.common.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AgreementRepository extends JpaRepository<Agreement, Long>, AgreementRepositoryCustom {
    List<Agreement> findByMemberAndTermIn(Member member, Set<Term> terms);
}
