package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.dto.TermDto;
import com.zeroskill.buytopia.entity.Term;
import com.zeroskill.buytopia.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {
    private final TermRepository termRepository;

    public List<TermDto> getTermsByIds(List<Long> termIds) {
        List<Term> terms = termRepository.findAllById(termIds);
        return terms.stream()
                .map(TermDto::of)
                .toList();
    }
}
