package com.rrtyui.repository;

import com.rrtyui.dto.MatchFilter;
import com.rrtyui.entity.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CrudRepository<K extends Serializable, E> implements Repository<K, E> {

    private final Class<E> clazz;
    private final EntityManager entityManager;


    @Override
    @Transactional
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        return entityManager.merge(entity);
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


    public List<Match> findAll(MatchFilter matchFilter) {
        return entityManager.createQuery(
                        "SELECT m FROM Match m " +
                                "JOIN m.player1 p1 " +
                                "JOIN m.player2 p2 " +
                                "WHERE p1.name LIKE :playerName OR p2.name LIKE :playerName", Match.class)
                .setParameter("playerName", "%" + matchFilter.name() + "%") // Используйте имя игрока из фильтра
                .setFirstResult(matchFilter.offset() * 5) // Установите смещение
                .setMaxResults(5) // Установите лимит
                .getResultList();
    }


}

