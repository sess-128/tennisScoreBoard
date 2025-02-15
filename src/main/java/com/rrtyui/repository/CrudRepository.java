package com.rrtyui.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
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
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(K id) {
        entityManager.remove(id);
        entityManager.flush();
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public Optional<E> findByName(String name) {
        TypedQuery<E> query = entityManager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e WHERE e.name = :name", clazz);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager
                .getCriteriaBuilder()
                .createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria).getResultList();
    }
}

