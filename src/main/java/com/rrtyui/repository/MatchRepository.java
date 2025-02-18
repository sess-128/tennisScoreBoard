package com.rrtyui.repository;

import com.rrtyui.dto.MatchFilter;
import com.rrtyui.entity.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;


public class MatchRepository extends CrudRepository<Long, Match> {

    @PersistenceContext
    private EntityManager entityManager;

    private static final int PAGE_SIZE = 5;

    public MatchRepository(EntityManager entityManager) {
        super(Match.class, entityManager);
    }

//    public List<Match> findAll(MatchFilter matchFilter) {
//        return entityManager.createQuery(
//                        "SELECT m FROM Match m " +
//                                "JOIN m.player1 p1 " +
//                                "JOIN m.player2 p2 " +
//                                "WHERE p1.name LIKE :playerName OR p2.name LIKE :playerName", Match.class)
//                .setParameter("playerName", "%" + matchFilter.name() + "%") // Используйте имя игрока из фильтра
//                .setFirstResult(matchFilter.offset() * 5) // Установите смещение
//                .setMaxResults(5) // Установите лимит
//                .getResultList();
//    }

    // offset = страница
//    (P — 1) x N, где P — это номер страницы, которая нам нужна, а N – это количество товаров, которые мы выводим на странице.
//
//    Значение N у нас фиксировано и указывается в блоке LIMIT. То есть сейчас это 2.
//    Значение P мы выбираем из набора целых чисел: 1, 2, 3 и тд.
//    Результат вычисления мы подставляем в блок OFFSET.
//
//    Сейчас мы делаем расчеты для третьей страницы, поэтому получаем:
//            (P — 1) x N = (3 — 1) x 2 = 4
    // лимит = кол-во записей
}
