package com.enginemobi.core.profile.domain;


import com.enginemobi.common.i18n.domain.ISOCountry;
import com.enginemobi.common.i18n.domain.ISOCountryImpl;
import com.enginemobi.common.time.domain.TemporalTimestampListener;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteEntityImpl;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@EntityListeners(value = { TemporalTimestampListener.class })
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ADDRESS")
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
public class AddressImpl extends BmSuiteEntityImpl<Long, Address> implements Address {

    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "ADDRESS_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    @Column(name = "ADDRESS_ID")
    protected Long id;

    @Column(name = "FULL_NAME")
    protected String fullName;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

    @Column(name = "EMAIL_ADDRESS")
    protected String emailAddress;

    @Column(name = "COMPANY_NAME")
    protected String companyName;

    @Column(name = "ADDRESS_LINE1", nullable = false)
    protected String addressLine1;

    @Column(name = "ADDRESS_LINE2")
    protected String addressLine2;

    @Column(name = "ADDRESS_LINE3")
    protected String addressLine3;

    @Column(name = "CITY", nullable = false)
    protected String city;


    @Column(name = "ISO_COUNTRY_SUB")
    protected String isoCountrySubdivision;

    @Column(name = "SUB_STATE_PROV_REG")
    protected String stateProvinceRegion;

    @Column(name = "COUNTY")
    protected String county;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = ISOCountryImpl.class)
    @JoinColumn(name = "ISO_COUNTRY_ALPHA2")
    @org.hibernate.annotations.Index(name="ADDRESS_ISO_COUNTRY_IDX", columnNames={"ISO_COUNTRY_ALPHA2"})
    protected ISOCountry isoCountryAlpha2;

    @Column(name = "POSTAL_CODE")
    protected String postalCode;

    @Column(name = "ZIP_FOUR")
    protected String zipFour;

    @ManyToOne(targetEntity = PhoneImpl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PHONE_PRIMARY_ID")
    @org.hibernate.annotations.Index(name="ADDRESS_PHONE_PRI_IDX", columnNames={"PHONE_PRIMARY_ID"})
    protected Phone phonePrimary;

    @ManyToOne(targetEntity = PhoneImpl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PHONE_SECONDARY_ID")
    @org.hibernate.annotations.Index(name="ADDRESS_PHONE_SEC_IDX", columnNames={"PHONE_SECONDARY_ID"})
    protected Phone phoneSecondary;

    @ManyToOne(targetEntity = PhoneImpl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PHONE_FAX_ID")
    @org.hibernate.annotations.Index(name="ADDRESS_PHONE_FAX_IDX", columnNames={"PHONE_FAX_ID"})
    protected Phone phoneFax;

    @Column(name = "IS_DEFAULT")
    protected boolean isDefault = false;

    @Column(name = "IS_ACTIVE")
    protected boolean isActive = true;

    @Column(name = "IS_BUSINESS")
    protected boolean isBusiness = false;

    /**
     * Intended to be used to differentiate whether this address is a physical address (e.g. street/house) or a mailing address (e.g. P.O. Box etc..)
     */
    @Column(name = "IS_STREET")
    protected boolean isStreet = false;

    /**
     * Intended to be used to differentiate whether this address is a physical address (e.g. street/house) or a mailing address (e.g. P.O. Box etc..)
     */
    @Column(name = "IS_MAILING")
    protected boolean isMailing = false;

    /**
     * This is intented to be used for address verification integrations and should not be modifiable in the admin
     */
    @Column(name = "TOKENIZED_ADDRESS")
    protected String tokenizedAddress;

    /**
     * This is intented to be used for address verification integrations and should not be modifiable in the admin
     */
    @Column(name = "STANDARDIZED")
    protected Boolean standardized = Boolean.FALSE;

    /**
     * This is intented to be used for address verification integrations and should not be modifiable in the admin
     */
    @Column(name = "VERIFICATION_LEVEL")
    protected String verificationLevel;

    @Column(name = "PRIMARY_PHONE")
    @Deprecated
    protected String primaryPhone;

    @Column(name = "SECONDARY_PHONE")
    @Deprecated
    protected String secondaryPhone;

    @Column(name = "FAX")
    @Deprecated
    protected String fax;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsoCountrySubdivision() {
        return isoCountrySubdivision;
    }

    public void setIsoCountrySubdivision(String isoCountrySubdivision) {
        this.isoCountrySubdivision = isoCountrySubdivision;
    }

    public String getStateProvinceRegion() {
        return stateProvinceRegion;
    }

    public void setStateProvinceRegion(String stateProvinceRegion) {
        this.stateProvinceRegion = stateProvinceRegion;
    }

    public ISOCountry getIsoCountryAlpha2() {
        return isoCountryAlpha2;
    }

    public void setIsoCountryAlpha2(ISOCountry isoCountryAlpha2) {
        this.isoCountryAlpha2 = isoCountryAlpha2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTokenizedAddress() {
        return tokenizedAddress;
    }

    public void setTokenizedAddress(String tokenizedAddress) {
        this.tokenizedAddress = tokenizedAddress;
    }

    public Boolean getStandardized() {
        return standardized;
    }

    public void setStandardized(Boolean standardized) {
        this.standardized = standardized;
    }

    public String getZipFour() {
        return zipFour;
    }

    public void setZipFour(String zipFour) {
        this.zipFour = zipFour;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Phone getPhonePrimary() {
        Phone legacyPhone = new PhoneImpl();
        legacyPhone.setPhoneNumber(this.primaryPhone);
        return (phonePrimary == null && this.primaryPhone !=null)? legacyPhone : phonePrimary;
    }

    public void setPhonePrimary(Phone phonePrimary) {
        this.phonePrimary = phonePrimary;
    }

    public Phone getPhoneSecondary() {
        Phone legacyPhone = new PhoneImpl();
        legacyPhone.setPhoneNumber(this.secondaryPhone);
        return (phoneSecondary == null && this.secondaryPhone !=null)? legacyPhone : phoneSecondary;
    }

    public void setPhoneSecondary(Phone phoneSecondary) {
        this.phoneSecondary = phoneSecondary;
    }

    public Phone getPhoneFax() {
        Phone legacyPhone = new PhoneImpl();
        legacyPhone.setPhoneNumber(this.fax);
        return (phoneFax == null && this.fax != null)? legacyPhone : phoneFax;
    }

    public void setPhoneFax(Phone phoneFax) {
        this.phoneFax = phoneFax;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean isBusiness) {
        this.isBusiness = isBusiness;
    }

    public boolean isStreet() {
        return isStreet;
    }

    public void setStreet(boolean isStreet) {
        this.isStreet = isStreet;
    }

    public boolean isMailing() {
        return isMailing;
    }

    public void setMailing(boolean isMailing) {
        this.isMailing = isMailing;
    }

    public String getVerificationLevel() {
        return verificationLevel;
    }

    public void setVerificationLevel(String verificationLevel) {
        this.verificationLevel = verificationLevel;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        AddressImpl other = (AddressImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (addressLine1 == null) {
            if (other.addressLine1 != null)
                return false;
        } else if (!addressLine1.equals(other.addressLine1))
            return false;
        if (addressLine2 == null) {
            if (other.addressLine2 != null)
                return false;
        } else if (!addressLine2.equals(other.addressLine2))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
            return false;
        if (isoCountryAlpha2 == null) {
            if (other.isoCountryAlpha2 != null)
                return false;
        } else if (!isoCountryAlpha2.equals(other.isoCountryAlpha2))
            return false;
        if (county == null) {
            if (other.county != null)
                return false;
        } else if (!county.equals(other.county))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (isoCountrySubdivision == null) {
            if (other.isoCountrySubdivision != null)
                return false;
        } else if (!isoCountrySubdivision.equals(other.isoCountrySubdivision))
            return false;
        if (stateProvinceRegion == null) {
            if (other.stateProvinceRegion != null)
                return false;
        } else if (!stateProvinceRegion.equals(other.stateProvinceRegion))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
        result = prime * result + ((addressLine2 == null) ? 0 : addressLine2.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((isoCountryAlpha2 == null) ? 0 : isoCountryAlpha2.hashCode());
        result = prime * result + ((county == null) ? 0 : county.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((isoCountrySubdivision == null) ? 0 : isoCountrySubdivision.hashCode());
        result = prime * result + ((stateProvinceRegion == null) ? 0 : stateProvinceRegion.hashCode());
        return result;
    }

}
