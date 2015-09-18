package com.enginemobi.core.store.domain;

import com.enginemobi.common.persistence.Status;
import com.enginemobi.core.reference.country.domain.Country;

import java.io.Serializable;
import java.util.Date;

/**
 * Represent merchant store
 */
public interface Store extends Status, Serializable {

     Long getId();
     void setId(Long id);

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



}
