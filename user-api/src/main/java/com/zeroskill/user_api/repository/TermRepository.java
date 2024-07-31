package com.zeroskill.user_api.repository;

import com.zeroskill.user_api.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long>, TermRepositoryCustom {
}
