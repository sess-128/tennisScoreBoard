package com.rrtyui.junit.service;

import com.rrtyui.entity.Match;
import com.rrtyui.entity.Player;
import com.rrtyui.repository.MatchRepository;
import com.rrtyui.repository.PlayerRepository;
import com.rrtyui.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.module.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class MatchRepositoryTest {

    private SessionFactory sessionFactory;
    private Session session;
    private MatchRepository matchRepository;
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        // Настройка Hibernate для использования H2 in-memory database


        sessionFactory = HibernateUtil.buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        matchRepository = new MatchRepository(session);
        playerRepository = new PlayerRepository(session);
    }

//    @AfterEach
//    void tearDown() {
//        session.close();
//        sessionFactory.close();
//    }

    @Test
    void testSaveMatch() {
        // Создаём игроков
        Player player1 = new Player();
        player1.setName("Player 1");

        Player player2 = new Player();
        player2.setName("Player 2");

        // Сохраняем игроков
        playerRepository.update(player1);
        playerRepository.update(player2);

        // Создаём матч
        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setWinner(player1);

        // Сохраняем матч
        Match savedMatch = matchRepository.save(match);

        // Проверяем, что матч сохранён
        assertNotNull(savedMatch.getId());
        assertEquals(player1.getName(), savedMatch.getPlayer1().getName());
        assertEquals(player2.getName(), savedMatch.getPlayer2().getName());
        assertEquals(player1.getName(), savedMatch.getWinner().getName());
    }
}

