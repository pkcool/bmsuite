package com.enginemobi.core.reference.country.domain;

import java.io.Serializable;

public interface Country extends Serializable{
    String getName();

    void setName(String name);

    boolean getSupported();

    void setSupported(boolean supported);

    String getIsoCode();

    void setIsoCode(String isoCode);
}
