package com.zeroskill.user_api.repository;

import com.zeroskill.user_api.entity.Term;
import com.zeroskill.user_api.entity.TermId;

import java.util.List;
import java.util.Set;

public interface TermRepositoryCustom {
    List<Term> findLatestActiveTermsByPurpose(String purpose);
    List<Term> findLatestActiveRequiredTermsByPurpose(String purpose);
    Set<Term> findByTermIds(List<TermId> termIds);
}
