package com.zeroskill.userapi.repository;

import com.zeroskill.userapi.entity.Agreement;
import com.zeroskill.userapi.entity.Member;
import com.zeroskill.userapi.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AgreementRepository extends JpaRepository<Agreement, Long>, AgreementRepositoryCustom {
    List<Agreement> findByMemberAndTermIn(Member member, Set<Term> terms);
}
