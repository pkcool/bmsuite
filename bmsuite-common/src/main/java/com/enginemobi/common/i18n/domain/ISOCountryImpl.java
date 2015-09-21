package com.enginemobi.common.i18n.domain;

import com.enginemobi.common.i18n.service.type.ISOCodeStatusType;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ISO_COUNTRY")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
public class ISOCountryImpl implements ISOCountry{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ALPHA_2")
    protected String alpha2;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "ALPHA_3")
    protected String alpha3;

    @Column(name = "NUMERIC_CODE")
    protected Integer numericCode;

    @Column(name = "STATUS")
    protected String status;

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    public Integer getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(Integer numericCode) {
        this.numericCode = numericCode;
    }

    public ISOCodeStatusType getStatus() {
        return ISOCodeStatusType.getInstance(status);
    }

    public void setStatus(ISOCodeStatusType status) {
        this.status = status == null ? null : status.getType();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        ISOCountryImpl other = (ISOCountryImpl) obj;
        if (alpha2 == null) {
            if (other.alpha2 != null)
                return false;
        } else if (!alpha2.equals(other.alpha2))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (alpha3 == null) {
            if (other.alpha3 != null)
                return false;
        } else if (!alpha3.equals(other.alpha3))
            return false;
        if (numericCode == null) {
            if (other.numericCode != null)
                return false;
        } else if (!numericCode.equals(other.numericCode))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alpha2 == null) ? 0 : alpha2.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((alpha3 == null) ? 0 : alpha3.hashCode());
        result = prime * result + ((numericCode == null) ? 0 : numericCode.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    public String getMainEntityName() {
        return getName();
    }

}

