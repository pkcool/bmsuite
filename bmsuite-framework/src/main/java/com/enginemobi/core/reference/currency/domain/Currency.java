package com.enginemobi.core.reference.currency.domain;

import com.enginemobi.core.generic.domain.BmSuiteEntity;


public interface Currency extends BmSuiteEntity<Long, Currency> {

    java.util.Currency getCurrency();

    void setCurrency(java.util.Currency currency);

    Boolean getSupported();

    void setSupported(Boolean supported);

    String getCode();

    String getSymbol();

    String getName();

    void setName(String name);
}
