package me.suryaakasam.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractDAO<E, K extends Serializable> implements DAO<E, K> {
    private final Class<? extends E> daoType;

    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    protected AbstractDAO() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        this.daoType = (Class<E>) parameterizedType.getActualTypeArguments()[0];
    }

    @Autowired
    public void setSessionFactory(EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    private Session createSession() {
        if (sessionFactory == null)
            throw new IllegalStateException("SessionFactory has not yet been initialized to provide a session");
        return sessionFactory.openSession();
    }

    @Override
    public E findById(K key) {
        E entity;

        try (Session session = createSession()) {
            entity = session.get(daoType, key);
        }

        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll() {
        List<E> entities;

        try (Session session = createSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<E> criteriaQuery = (CriteriaQuery<E>) criteriaBuilder.createQuery(daoType);

            Root<E> entityRoot = (Root<E>) criteriaQuery.from(daoType);
            criteriaQuery.select(entityRoot);
            //criteriaQuery.where(criteriaBuilder.equal(entityRoot.get("id"), id));

            Query<E> query = session.createQuery(criteriaQuery);
            entities = query.getResultList();
        }

        return entities;
    }

    @Override
    public E save(E entity) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }

        return entity;
    }

    @Override
    public E update(E entity) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }

        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(E entity) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            E mergedEntity = (E) session.merge(entity);
            session.delete(mergedEntity);
            transaction.commit();
        }
    }
}

