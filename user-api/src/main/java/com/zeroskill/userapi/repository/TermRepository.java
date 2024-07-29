package com.zeroskill.userapi.repository;

import com.zeroskill.userapi.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long>, TermRepositoryCustom {
}
