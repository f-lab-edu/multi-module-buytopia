package com.zeroskill.buytopia.repository;

import com.zeroskill.buytopia.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long>, TermRepositoryCustom {
}
