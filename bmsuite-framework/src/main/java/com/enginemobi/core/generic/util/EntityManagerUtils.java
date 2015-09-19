package com.enginemobi.core.generic.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Component
public class EntityManagerUtils {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager openEntityManager() {
		if (TransactionSynchronizationManager.hasResource(entityManagerFactory)) {
			return ((EntityManagerHolder) TransactionSynchronizationManager.getResource(entityManagerFactory)).getEntityManager();
		} else {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			TransactionSynchronizationManager.bindResource(entityManagerFactory, new EntityManagerHolder(entityManager));
			return entityManager;
		}
	}
	
	public void closeEntityManager() {
		if (TransactionSynchronizationManager.hasResource(entityManagerFactory)) {
			EntityManagerHolder entityManagerHolder = (EntityManagerHolder) TransactionSynchronizationManager.unbindResource(entityManagerFactory);
			EntityManagerFactoryUtils.closeEntityManager(entityManagerHolder.getEntityManager());
		}
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
