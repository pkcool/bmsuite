package com.enginemobi.core.reference.language.domain;

import com.enginemobi.core.generic.domain.BmSuiteEntity;

public interface Language extends BmSuiteEntity<Integer, Language> {
    String getCode();

    void setCode(String code);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);
}
