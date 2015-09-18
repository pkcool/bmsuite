package com.enginemobi.core.reference.currency.domain;

import com.enginemobi.core.generic.domain.BmSuiteBaseEntity;


public interface Currency extends BmSuiteBaseEntity<Long, Currency> {

    java.util.Currency getCurrency();

    void setCurrency(java.util.Currency currency);

    Boolean getSupported();

    void setSupported(Boolean supported);

    String getCode();

    String getSymbol();

    String getName();

    void setName(String name);
}
