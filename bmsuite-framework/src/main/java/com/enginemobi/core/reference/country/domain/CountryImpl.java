package com.enginemobi.core.reference.country.domain;

import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteEntityImpl;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRY", schema= SchemaConstant.BMSUITEDB_SCHEMA)
@Cacheable
public class CountryImpl extends BmSuiteEntityImpl<Integer,Country> implements Country {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="COUNTRY_ID")
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "COUNTRY_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    private Integer id;

    @Column(name = "COUNTRY_SUPPORTED")
    private boolean supported = true;

    @Column(name = "COUNTRY_ISOCODE", unique=true, nullable = false)
    private String isoCode;

    @Transient
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryImpl() {
    }

    public CountryImpl(String isoCode) {
        this.setIsoCode(isoCode);
    }

    public boolean getSupported() {
        return supported;
    }

    public void setSupported(boolean supported) {
        this.supported = supported;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}
