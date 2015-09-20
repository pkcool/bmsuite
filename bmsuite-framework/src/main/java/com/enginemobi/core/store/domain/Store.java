package com.enginemobi.core.store.domain;

import com.enginemobi.common.persistence.Status;
import com.enginemobi.core.generic.domain.BmSuiteEntity;
import com.enginemobi.core.reference.country.domain.Country;
import com.enginemobi.core.reference.currency.domain.Currency;
import com.enginemobi.core.reference.language.domain.Language;

import java.util.Date;
import java.util.List;

/**
 * Represent merchant store
 */
public interface Store extends Status, BmSuiteEntity<Long, Store> {

    String getCode();

    void setCode(String code);

    String getName();

    void setName(String name);

    // store address
    String getAddress1();

    void setAddress1(String address1);

    String getAddress2();

    void setAddress2(String address2);

    String getSuburb();

    void setSuburb(String suburb);

    void setState(String state);

    String getState();

    String getPostcode();

    void setPostcode(String postcode);

    Country getCountry();

    void setCountry(Country country);

    String getPhone();

    void setPhone(String phone);

    String getFax();

    void setFax(String fax);

    String getEmail();

    void setEmail(String email);

    String getWebsiteUrl();

    void setWebsiteUrl(String websiteUrl);

    Date getInBusinessSince();

    void setInBusinessSince(Date inBusinessSince);

    Currency getCurrency();

    void setCurrency(Currency currency);

    Language getDefaultLanguage();

    void setDefaultLanguage(Language defaultLanguage);

    List<Language> getLanguages();

    void setLanguages(List<Language> languages);
}
