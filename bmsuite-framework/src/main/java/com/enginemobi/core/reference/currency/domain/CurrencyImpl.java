package com.enginemobi.core.reference.currency.domain;

import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteEntityImpl;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "CURRENCY", schema = SchemaConstant.BMSUITEDB_SCHEMA)
@Cacheable
public class CurrencyImpl extends BmSuiteEntityImpl<Long, Currency> implements Currency {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CURRENCY_ID")
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "CURRENCY_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    private Long id;

    @Column(name = "CURRENCY_CODE" ,nullable = false, unique = true)
    private java.util.Currency currency;

    @Column(name = "SUPPORTED")
    private Boolean supported = true;

    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "NAME", unique = true)
    private String name;

    public CurrencyImpl() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(java.util.Currency currency) {
        this.currency = currency;
        this.code = currency.getCurrencyCode();
    }

    public Boolean getSupported() {
        return supported;
    }

    public void setSupported(Boolean supported) {
        this.supported = supported;
    }

    public String getCode() {
        if (currency.getCurrencyCode() != code) {
            return currency.getCurrencyCode();
        }
        return code;
    }

    public String getSymbol() {
        return currency.getSymbol();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
