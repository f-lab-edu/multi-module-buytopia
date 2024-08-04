package com.zeroskill.common.repository;

import com.zeroskill.common.entity.Agreement;

import java.util.List;

public interface AgreementRepositoryCustom {
    List<Agreement> bulkInsert(List<Agreement> agreements);
}
