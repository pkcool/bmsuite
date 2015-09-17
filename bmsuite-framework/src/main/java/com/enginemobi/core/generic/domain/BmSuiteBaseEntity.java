package com.enginemobi.core.generic.domain;

import java.io.Serializable;

/**
 * Created by smaxll on 17/09/2015.
 */
public interface BmSuiteBaseEntity<K extends Serializable & Comparable<K>, E extends BmSuiteBaseEntity<K, ?>>
        extends Serializable, Comparable<E>  {

        K getId();

        void setId(K id);

        boolean isNew();

}
