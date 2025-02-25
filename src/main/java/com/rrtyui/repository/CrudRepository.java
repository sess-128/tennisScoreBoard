package com.rrtyui.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CrudRepository<K extends Serializable, E> implements Repository<K, E> {

    private final Class<E> clazz;
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        return entityManager.merge(entity);
    }

    @Override
    public Optional<E> findByName(String name) {
        TypedQuery<E> query = entityManager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e WHERE e.name = :name", clazz);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }
}

