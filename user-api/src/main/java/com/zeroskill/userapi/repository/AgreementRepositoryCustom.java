package com.zeroskill.userapi.repository;

import com.zeroskill.userapi.entity.Agreement;

import java.util.List;

public interface AgreementRepositoryCustom {
    List<Agreement> bulkInsert(List<Agreement> agreements);
}
