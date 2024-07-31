package com.zeroskill.user_api.repository;

import com.zeroskill.user_api.entity.Agreement;
import com.zeroskill.user_api.entity.Member;
import com.zeroskill.user_api.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AgreementRepository extends JpaRepository<Agreement, Long>, AgreementRepositoryCustom {
    List<Agreement> findByMemberAndTermIn(Member member, Set<Term> terms);
}
