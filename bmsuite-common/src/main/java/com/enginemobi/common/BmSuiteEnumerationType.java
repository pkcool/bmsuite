package com.enginemobi.common;

/**
 * In order for the Administration to display enumerated values with meaningful labels,
 * enumerations should implement this interface.
 *
 */
public interface BmSuiteEnumerationType {
    String getType();
    String getFriendlyType();
}
