package com.rrtyui.repository;

import com.rrtyui.entity.Match;
import jakarta.persistence.EntityManager;


public class MatchRepository extends CrudRepository<Long, Match> {

    public MatchRepository(EntityManager entityManager) {
        super(Match.class, entityManager);
    }
}
