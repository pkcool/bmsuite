package com.enginemobi.core.reference.country.domain;

import com.enginemobi.core.generic.domain.BmSuiteBaseEntity;


public interface Country extends BmSuiteBaseEntity<Integer, Country>{
    String getName();

    void setName(String name);

    boolean getSupported();

    void setSupported(boolean supported);

    String getIsoCode();

    void setIsoCode(String isoCode);
}
