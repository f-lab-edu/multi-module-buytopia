package com.zeroskill.common.repository;

import com.zeroskill.common.entity.Term;
import com.zeroskill.common.entity.TermId;

import java.util.List;
import java.util.Set;

public interface TermRepositoryCustom {
    List<Term> findLatestActiveTermsByPurpose(String purpose);
    List<Term> findLatestActiveRequiredTermsByPurpose(String purpose);
    Set<Term> findByTermIds(List<TermId> termIds);
}
