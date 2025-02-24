package com.rrtyui.repository;

import com.rrtyui.dto.MatchFilter;
import com.rrtyui.dto.MatchPageResponseDto;
import com.rrtyui.entity.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CrudRepository<K extends Serializable, E> implements Repository<K, E> {

    private final Class<E> clazz;
    private final EntityManager entityManager;
    private static final int PAGE_SIZE = 5;


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

    public MatchPageResponseDto findAllWithPagination(MatchFilter matchFilter) {
        long totalMatches = countAllMatches(matchFilter);

        int totalPages = (int) Math.ceil((double) totalMatches / PAGE_SIZE);

        if (matchFilter.offset() > totalPages) {
            matchFilter = new MatchFilter(totalPages, matchFilter.name());
        }

        int offset = (matchFilter.offset() - 1) * PAGE_SIZE;

        String queryString = "SELECT m FROM Match m " +
                "JOIN m.player1 p1 " +
                "JOIN m.player2 p2 ";

        if (matchFilter.name() != null && !matchFilter.name().isEmpty()) {
            queryString += "WHERE p1.name LIKE :playerName OR p2.name LIKE :playerName ";
        }

        TypedQuery<Match> query = entityManager.createQuery(queryString, Match.class);

        if (matchFilter.name() != null && !matchFilter.name().isEmpty()) {
            query.setParameter("playerName", "%" + matchFilter.name() + "%");
        }

        query.setFirstResult(offset)
                .setMaxResults(PAGE_SIZE);

        List<Match> matches = query.getResultList();

        return new MatchPageResponseDto(matches, matchFilter.offset(), totalPages);
    }

    public long countAllMatches(MatchFilter matchFilter) {
        String queryString = "SELECT COUNT(m) FROM Match m " +
                "JOIN m.player1 p1 " +
                "JOIN m.player2 p2 ";

        if (matchFilter.name() != null && !matchFilter.name().isEmpty()) {
            queryString += "WHERE p1.name LIKE :playerName OR p2.name LIKE :playerName ";
        }

        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);

        if (matchFilter.name() != null && !matchFilter.name().isEmpty()) {
            query.setParameter("playerName", "%" + matchFilter.name() + "%");
        }

        return query.getSingleResult();
    }

}

