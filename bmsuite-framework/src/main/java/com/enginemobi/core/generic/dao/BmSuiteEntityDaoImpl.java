package com.enginemobi.core.generic.dao;

import com.enginemobi.core.common.Criterion;
import com.enginemobi.core.common.SearchCriteria;
import com.enginemobi.core.generic.domain.BmSuiteEntity;
import com.enginemobi.core.generic.util.GenericEntityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

/**
 * @param <E> entity type
 * @Param <K> id </K>
 */
public abstract class BmSuiteEntityDaoImpl<K extends Serializable & Comparable<K>, E extends BmSuiteEntity<K, ?>>
        extends BmSuiteJpaDaoSupport
        implements BmSuiteEntityDao<K, E>, SearchableDao<E> {

    private Class<E> objectClass;

    @SuppressWarnings("unchecked")
    public BmSuiteEntityDaoImpl() {
        this.objectClass = (Class<E>) GenericEntityUtils.getGenericEntityClassFromComponentDefinition(getClass());
    }

    protected final Class<E> getObjectClass() {
        return objectClass;
    }


    public E getEntity(Class<? extends E> clazz, K id) {
        return super.getEntity(getObjectClass(), id);
    }


    public E getById(K id) {
        return super.getEntity(getObjectClass(), id);
    }


    public <V> E getByField(SingularAttribute<? super E, V> attribute, V fieldValue) {
        return super.getByField(getObjectClass(), attribute, fieldValue);
    }


    public void update(E entity) {
        super.update(entity);
    }


    public void save(E entity) {
        super.save(entity);
    }


    public void delete(E entity) {
        super.delete(entity);
    }


    public E refresh(E entity) {
        return super.refresh(entity);
    }


    public List<E> list() {
        return super.listEntity(getObjectClass());
    }


    public <V> List<E> listByField(SingularAttribute<? super E, V> attribute, V fieldValue) {
        return super.listEntityByField(getObjectClass(), attribute, fieldValue);
    }


    public <T extends E> List<T> list(Class<T> objectClass, Expression<Boolean> filter, Integer limit, Integer offset, Order... orders) {
        return super.listEntity(objectClass, filter, limit, offset, orders);
    }


    public Long count() {
        return super.countEntity(getObjectClass());
    }


    public <V> Long countByField(SingularAttribute<? super E, V> attribute, V fieldValue) {
        return super.countEntityByField(getObjectClass(), attribute, fieldValue);
    }


    public Long count(Expression<Boolean> filter) {
        return super.countEntity(getObjectClass(), filter);
    }


    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    public Page<E> search(SearchCriteria criteria, Pageable pageable)
    {
        CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<E> countRoot = countCriteria.from(this.objectClass);
        long total = this.getEntityManager().createQuery(
                countCriteria.select(builder.count(countRoot))
                        .where(toPredicates(criteria, countRoot, builder))
        ).getSingleResult();

        CriteriaQuery<E> pageCriteria = builder.createQuery(this.objectClass);
        Root<E> pageRoot = pageCriteria.from(this.objectClass);
        List<E> list = this.getEntityManager().createQuery(
                pageCriteria.select(pageRoot)
                        .where(toPredicates(criteria, pageRoot, builder))
                        .orderBy(toOrders(pageable.getSort(), pageRoot, builder))
        ).setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<E>(new ArrayList<E>(list), pageable, total);
    }

    private static Predicate[] toPredicates(SearchCriteria criteria, Root<?> root,
                                            CriteriaBuilder builder)
    {
        Predicate[] predicates = new Predicate[criteria.size()];
        int i = 0;
        for(Criterion c : criteria)
            predicates[i++] = c.getOperator().toPredicate(c, root, builder);
        return predicates;
    }
}
