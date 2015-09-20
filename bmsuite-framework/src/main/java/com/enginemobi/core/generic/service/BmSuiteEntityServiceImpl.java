package com.enginemobi.core.generic.service;

import com.enginemobi.core.generic.dao.BmSuiteEntityDao;
import com.enginemobi.core.generic.domain.BmSuiteEntity;
import javax.persistence.metamodel.SingularAttribute;

import com.enginemobi.core.generic.exception.ServiceException;
import com.enginemobi.core.generic.util.GenericEntityUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @param <E> entity type
 */
public abstract class BmSuiteEntityServiceImpl<K extends Serializable & Comparable<K>, E extends BmSuiteEntity<K, ?>>
	implements BmSuiteEntityService<K, E> {
	
	/**
	 * Class type of the generic entity
	 */
	private Class<E> objectClass;

	private BmSuiteEntityDao<K, E> genericDao;

	@SuppressWarnings("unchecked")
	public BmSuiteEntityServiceImpl(BmSuiteEntityDao<K, E> genericDao) {
		this.genericDao = genericDao;
		
		this.objectClass = (Class<E>) GenericEntityUtils.getGenericEntityClassFromComponentDefinition(getClass());
	}
	
	protected final Class<E> getObjectClass() {
		return objectClass;
	}

	
	public E getEntity(Class<? extends E> clazz, K id) {
		return genericDao.getEntity(clazz, id);
	}

	
	public E getById(K id) {
		return genericDao.getById(id);
	}

	/**
	 * @param fieldName condition field
	 * @param fieldValue field value
	 * @return entity
	 */
	protected <V> E getByField(SingularAttribute<? super E, V> fieldName, V fieldValue) {
		return genericDao.getByField(fieldName, fieldValue);
	}
	
	public void save(E entity) throws ServiceException {
		genericDao.save(entity);
	}
	
	
	public void create(E entity) throws ServiceException {
		createEntity(entity);
	}
	
	
	protected void createEntity(E entity) throws ServiceException {
		save(entity);
	}
	
	
	public final void update(E entity) throws ServiceException {
		updateEntity(entity);
	}
	
	protected void updateEntity(E entity) throws ServiceException {
		genericDao.update(entity);
	}
	
	
	public void delete(E entity) throws ServiceException {
		genericDao.delete(entity);
	}
	
	
	public void flush() {
		genericDao.flush();
	}
	
	
	public void clear() {
		genericDao.clear();
	}
	
	
	public E refresh(E entity) {
		return genericDao.refresh(entity);
	}

	
	public List<E> list() {
		return genericDao.list();
	}
	
	protected <V> List<E> listByField(SingularAttribute<? super E, V> fieldName, V fieldValue) {
		return genericDao.listByField(fieldName, fieldValue);
	}
	
	
	public Long count() {
		return genericDao.count();
	}

}