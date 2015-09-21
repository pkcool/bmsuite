package com.enginemobi.core.profile.domain;

import java.util.Map;

/**
 * this interface indicates if the domain objects use additional fields
 * It is not mandatory,  but it is useful in order to process those fields in a generic way when generating JAXB Wrappers
 */
public interface AdditionalFields {

    Map<String, String> getAdditionalFields();

    void setAdditionalFields(Map<String, String> additionalFields);

}
