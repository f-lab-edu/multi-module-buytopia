package com.zeroskill.user_api.repository;

import com.zeroskill.user_api.entity.Agreement;

import java.util.List;

public interface AgreementRepositoryCustom {
    List<Agreement> bulkInsert(List<Agreement> agreements);
}
