package com.enginemobi.core.profile.domain;

import com.enginemobi.core.generic.domain.BmSuiteEntity;

 public interface Phone extends BmSuiteEntity<Long, Phone> {

     String getCountryCode();

     void setCountryCode(String countryCode);

     String getPhoneNumber();

     void setPhoneNumber(String phoneNumber);

     String getExtension();

     void setExtension(String extension);

     boolean isDefault();

     void setDefault(boolean isDefault);

     boolean isActive();

     void setActive(boolean isActive);
}
