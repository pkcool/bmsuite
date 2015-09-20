package com.enginemobi.core.generic.domain;

import java.io.Serializable;

/**
 * base generic entity
 * @param <K> primary key
 * @param <E> entity
 */
public interface BmSuiteEntity<K extends Serializable & Comparable<K>, E extends BmSuiteEntity<K, ?>>
        extends Serializable, Comparable<E>  {

        K getId();

        void setId(K id);

        boolean isNew();

}
