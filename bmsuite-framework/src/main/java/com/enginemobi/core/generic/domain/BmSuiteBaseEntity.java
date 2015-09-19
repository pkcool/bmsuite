package com.enginemobi.core.generic.domain;

import java.io.Serializable;

/**
 * base generic entity
 * @param <K> primary key
 * @param <E> entity
 */
public interface BmSuiteBaseEntity<K extends Serializable & Comparable<K>, E extends BmSuiteBaseEntity<K, ?>>
        extends Serializable, Comparable<E>  {

        K getId();

        void setId(K id);

        boolean isNew();

}
