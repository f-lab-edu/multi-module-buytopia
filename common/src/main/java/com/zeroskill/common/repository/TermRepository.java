package com.zeroskill.common.repository;

import com.zeroskill.common.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long>, TermRepositoryCustom {
}
