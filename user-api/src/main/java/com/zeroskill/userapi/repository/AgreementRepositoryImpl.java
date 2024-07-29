package com.zeroskill.userapi.repository;

import com.zeroskill.userapi.entity.Agreement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class AgreementRepositoryImpl implements AgreementRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Agreement> bulkInsert(List<Agreement> agreements) {
        if (agreements.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Native SQL building
        String sql = "INSERT INTO agreement (member_id, term_title, term_version, agreed, agreed_date) VALUES " +
                agreements.stream()
                        .map(unit -> String.format("(%d, '%s', '%s', %d, '%s')",
                                unit.getMember().getId(),
                                unit.getTerm().getId().getTitle(),
                                unit.getTerm().getId().getVersion(),
                                1,
                                LocalDateTime.now().format(formatter)))
                        .collect(Collectors.joining(", "));

        entityManager.createNativeQuery(sql).executeUpdate();
        return agreements;
    }
}
