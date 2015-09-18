package com.enginemobi.core.reference.language.domain;

import com.enginemobi.core.generic.domain.BmSuiteBaseEntity;

public interface Language extends BmSuiteBaseEntity<Integer, Language>{
    String getCode();

    void setCode(String code);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);
}
