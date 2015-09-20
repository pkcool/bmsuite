package com.enginemobi.core.reference.country.domain;

import com.enginemobi.core.generic.domain.BmSuiteEntity;


public interface Country extends BmSuiteEntity<Integer, Country> {
    String getName();

    void setName(String name);

    boolean getSupported();

    void setSupported(boolean supported);

    String getIsoCode();

    void setIsoCode(String isoCode);
}
