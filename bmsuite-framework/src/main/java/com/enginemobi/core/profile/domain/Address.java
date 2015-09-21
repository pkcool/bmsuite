package com.enginemobi.core.profile.domain;

import com.enginemobi.common.i18n.domain.ISOCountry;
import com.enginemobi.core.generic.domain.BmSuiteEntity;


 public interface Address extends BmSuiteEntity<Long, Address>{

     void setAddressLine1(String addressLine1);

     String getAddressLine1();

     void setAddressLine2(String addressLine2);

     String getAddressLine2();

     void setAddressLine3(String addressLine3);

     String getAddressLine3();

     void setCity(String city);

     String getCity();

    /**
     * gets the ISO 3166-2 code for the country subdivision (state/region/province) where this address resides
     * @return - the code
     */
     String getIsoCountrySubdivision();

    /**
     * sets the ISO 3166-2 code for the country subdivision (state/region/province) where this address resides
     * @param isoCountrySubdivision - ISO 3166-2 country subdivision code
     */
     void setIsoCountrySubdivision(String isoCountrySubdivision);

    /**
     * @return - a friendly name indicating a countries subdivision, i.e. State, Province, Region etc...
     */
     String getStateProvinceRegion();

    /**
     * sets the friendly name indicating a countries subdivision, i.e. State, Province, Region etc...
     * @param stateProvinceRegion - friendly name
     */
     void setStateProvinceRegion(String stateProvinceRegion);

     void setPostalCode(String postalCode);

     String getPostalCode();

     String getCounty();

     void setCounty(String county);

     String getZipFour();

     void setZipFour(String zipFour);


    /**
     * gets the ISO 3166-1 alpha-2 code for the country where this address resides
     * @return - the ISOCountry representation of the code
     */
     ISOCountry getIsoCountryAlpha2();

    /**
     * sets the ISO 3166-1 alpha-2 code for the country where this address resides
     * @param isoCountryAlpha2 - ISO 3166-1 alpha-2 code
     */
     void setIsoCountryAlpha2(ISOCountry isoCountryAlpha2);

     String getTokenizedAddress();

     void setTokenizedAddress(String tAddress);

     Boolean getStandardized();

     void setStandardized(Boolean standardized);

     String getCompanyName();

     void setCompanyName(String companyName);

     boolean isDefault();

     void setDefault(boolean isDefault);

     String getFirstName();

     void setFirstName(String firstName);

     String getLastName();

     void setLastName(String lastName);

     String getFullName();

     void setFullName(String fullName);


     Phone getPhonePrimary();

     void setPhonePrimary(Phone phonePrimary);

     Phone getPhoneSecondary();

     void setPhoneSecondary(Phone phoneSecondary);

     Phone getPhoneFax();

     void setPhoneFax(Phone phone);

     String getEmailAddress();

     void setEmailAddress(String emailAddress);

     boolean isBusiness();

     void setBusiness(boolean isBusiness);

     boolean isStreet();

     void setStreet(boolean isStreet);

     boolean isMailing();

     void setMailing(boolean isMailing);

     String getVerificationLevel();

     void setVerificationLevel(String verificationLevel);

     boolean isActive();

     void setActive(boolean isActive);
}

