package com.zeroskill.buytopia.repository;

import com.zeroskill.buytopia.entity.Agreement;
import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AgreementRepository extends JpaRepository<Agreement, Long>, AgreementRepositoryCustom {
    List<Agreement> findByMemberAndTermIn(Member member, Set<Term> terms);
}
