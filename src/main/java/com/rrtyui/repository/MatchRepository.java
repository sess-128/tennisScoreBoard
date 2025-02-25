package com.rrtyui.repository;

import com.rrtyui.dto.MatchFilter;
import com.rrtyui.dto.MatchPageResponseDto;
import com.rrtyui.entity.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class MatchRepository extends CrudRepository<Long, Match> {
    @PersistenceContext
    private EntityManager entityManager;

    private static final int PAGE_SIZE = 5;
    private static final String SEARCH_BY_NAME_FILTER = "WHERE p1.name LIKE :playerName OR p2.name LIKE :playerName ";
    private static final String JOIN_PLAYERS_SQL = "JOIN m.player1 p1 JOIN m.player2 p2 ";


    public MatchRepository(EntityManager entityManager) {
        super(Match.class, entityManager);
        this.entityManager = entityManager;
    }

    public MatchPageResponseDto findAllWithPagination(MatchFilter matchFilter) {
        long totalMatches = countAllMatches(matchFilter);

        int totalPages = (int) Math.ceil((double) totalMatches / PAGE_SIZE);


        if (matchFilter.page() < 1) {
            matchFilter = new MatchFilter(1, matchFilter.name());
        } else if (matchFilter.page() > totalPages) {
            matchFilter = new MatchFilter(totalPages, matchFilter.name());
        }


        int offset = Math.max(0, (matchFilter.page() - 1) * PAGE_SIZE);

        String queryString = "SELECT m FROM Match m " + JOIN_PLAYERS_SQL;

        if (matchFilter.name() != null && !matchFilter.name().isEmpty()) {
            queryString += SEARCH_BY_NAME_FILTER;
        }

        TypedQuery<Match> query = entityManager.createQuery(queryString, Match.class);

        if (matchFilter.name() != null && !matchFilter.name().isEmpty()) {
            query.setParameter("playerName", "%" + matchFilter.name() + "%");
        }

        query.setFirstResult(offset)
                .setMaxResults(PAGE_SIZE);

        List<Match> matches = query.getResultList();

        return new MatchPageResponseDto(matches, matchFilter.page() , totalPages);
    }
    private long countAllMatches(MatchFilter matchFilter) {
        String queryString = "SELECT COUNT(m) FROM Match m " + JOIN_PLAYERS_SQL;

        var isNameExist = matchFilter.name() != null && !matchFilter.name().isEmpty();
        if (isNameExist) {
            queryString += SEARCH_BY_NAME_FILTER;
        }

        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);

        if (isNameExist) {
            query.setParameter("playerName", "%" + matchFilter.name() + "%");
        }

        return query.getSingleResult();
    }



}
