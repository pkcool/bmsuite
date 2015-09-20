package com.enginemobi.core.generic.service;

import com.enginemobi.core.generic.domain.BmSuiteEntity;
import com.enginemobi.core.generic.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Generic service interface</p>
 *
 * @param <K> type Primary Key
 */
public interface BmSuiteEntityService<K extends Serializable & Comparable<K>, E extends BmSuiteEntity<K, ?>> extends TransactionalAspectAwareService{

    /**
     * @param entity entity
     */
    void save(E entity) throws ServiceException;

    /**
     *
     * @param entity
     */
    void update(E entity) throws ServiceException;

    /**
     *
     * @param entity
     */
    void create(E entity) throws ServiceException;

    /**
     *
     * @param entity
     */
    void delete(E entity) throws ServiceException;

    /**
     *
     * @param entity
     */
    E refresh(E entity);


    /**
     *
     * @param id identification
     * @return
     */
    E getById(K id);

    /**
     *
     * @return list of entities
     */
    List<E> list();

    E getEntity(Class<? extends E> clazz, K id);

    Long count();

    /**
     * flush the session
     */
    void flush();

    void clear();

}
