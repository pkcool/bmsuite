package com.enginemobi.core.store.domain;

import com.enginemobi.common.persistence.ArchiveStatus;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.reference.country.domain.Country;
import com.enginemobi.core.reference.country.domain.CountryImpl;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "MERCHANT_STORE", schema= SchemaConstant.BMSUITEDB_SCHEMA)
public class StoreImpl implements Store {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STORE_ID", unique = true, nullable = false)
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "STORE_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    private Long id;

    @Column(name = "STORE_NAME", nullable = false)
    private String name;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name = "STORE_CODE", nullable=false, unique=true, length=100)
    private String code;

    @Column(name = "STORE_FAX")
    private String fax;

    @Column(name = "STORE_EMAIL")
    private String email;

    @Column(name = "STORE_WEBSITE_URL")
    private String websiteUrl;

    @Column(name = "ADDRESS_1")
    private String address1;

    @Column(name = "ADDRESS_2")
    private String address2;

    @Column(name = "STORE_SUBURB")
    private String suburb;

    @Column(name = "STORE_STATE")
    private String state;

    @Column(name = "STORE_POSTCODE")
    private String postcode;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CountryImpl.class)
    @JoinColumn(name="COUNTRY_ID", nullable=false, updatable=true)
    private Country country;

    @Column(name = "STORE_PHONE", length=50)
    private String phone;

    @Temporal(TemporalType.DATE)
    @Column(name = "IN_BUSINESS_SINCE")
    private Date inBusinessSince = new Date();


    @Embedded
    protected ArchiveStatus archiveStatus = new ArchiveStatus();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Date getInBusinessSince() {
        return inBusinessSince;
    }

    public void setInBusinessSince(Date inBusinessSince) {
        this.inBusinessSince = inBusinessSince;

    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Character getArchived() {
        ArchiveStatus temp;
        if (archiveStatus == null) {
            temp = new ArchiveStatus();
        } else {
            temp = archiveStatus;
        }
        return temp.getArchived();
    }

    public void setArchived(Character archived) {
        if (archiveStatus == null) {
            archiveStatus = new ArchiveStatus();
        }
        archiveStatus.setArchived(archived);
    }

    public boolean isActive() {
        return 'Y'!=getArchived();
    }

}
