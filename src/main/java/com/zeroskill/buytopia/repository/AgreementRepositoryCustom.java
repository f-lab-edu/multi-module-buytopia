package com.zeroskill.buytopia.repository;

import com.zeroskill.buytopia.entity.Agreement;

import java.util.List;

public interface AgreementRepositoryCustom {
    List<Agreement> bulkInsert(List<Agreement> agreements);
}
