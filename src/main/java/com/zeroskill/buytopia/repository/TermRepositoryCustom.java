package com.zeroskill.buytopia.repository;

import com.zeroskill.buytopia.entity.Term;
import com.zeroskill.buytopia.entity.TermId;

import java.util.List;

public interface TermRepositoryCustom {
    List<Term> findLatestActiveTermsByPurpose(String purpose);
    List<Term> findLatestActiveRequiredTermsByPurpose(String purpose);
    List<Term> findByTermIds(List<TermId> termIds);
}
