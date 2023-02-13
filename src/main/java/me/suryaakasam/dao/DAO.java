package me.suryaakasam.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<E, K extends Serializable> {
    E findById(K key);
    List<E> findAll();
    E save(E entity);
    E update(E entity);
    void delete(E entity);
}
