package com.zeroskill.userapi.repository;

import com.zeroskill.userapi.entity.Term;
import com.zeroskill.userapi.entity.TermId;

import java.util.List;
import java.util.Set;

public interface TermRepositoryCustom {
    List<Term> findLatestActiveTermsByPurpose(String purpose);
    List<Term> findLatestActiveRequiredTermsByPurpose(String purpose);
    Set<Term> findByTermIds(List<TermId> termIds);
}
