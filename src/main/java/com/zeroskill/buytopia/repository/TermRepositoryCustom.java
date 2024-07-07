package com.zeroskill.buytopia.repository;

import com.zeroskill.buytopia.entity.Term;

import java.util.List;

public interface TermRepositoryCustom {
    List<Term> findLatestActiveTermsByPurpose(String purpose);
}
