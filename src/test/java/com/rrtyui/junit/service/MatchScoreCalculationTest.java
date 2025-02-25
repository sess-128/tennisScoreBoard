package com.rrtyui.junit.service;

import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.entity.Player;
import com.rrtyui.service.matchScoreCalculationService.util.strategy.AdvantageStrategy;
import com.rrtyui.service.matchScoreCalculationService.util.strategy.TieBreakStrategy;
import com.rrtyui.service.matchScoreCalculationService.util.strategy.UsualStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchScoreCalculationTest {



    @Test
    void testWinGameAt40_0() {
        Player player = new Player(1L, "LOh");
        Player player2 = new Player(2L, "LOh2");
        var match = new MatchScoreModel(player, player2);
        match.setPlayer1Points(40); // 40
        match.setPlayer2Points(0); // 0

        UsualStrategy strategy = new UsualStrategy(match);
        strategy.addPointToFirstPlayer();

        assertEquals(0, match.getPlayer1Points());
        assertEquals(0, match.getPlayer2Points());
        assertEquals(1, match.getPlayer1Games());
    }

    @Test
    void testDeuceScenario() {
        Player player = new Player(1L, "LOh");
        Player player2 = new Player(2L, "LOh2");
        var match = new MatchScoreModel(player, player2);
        match.setPlayer1Points(40);
        match.setPlayer2Points(40);

        AdvantageStrategy strategy = new AdvantageStrategy(match);
        strategy.addPointToFirstPlayer();

        assertEquals(50, match.getPlayer1Points());
        assertEquals(40, match.getPlayer2Points());
        assertEquals(0, match.getPlayer1Games());
    }

    @Test
    void testTieBreakStartsAt6_6() {
        Player player = new Player(1L, "LOh");
        Player player2 = new Player(2L, "LOh2");
        var match = new MatchScoreModel(player, player2);
        match.setPlayer1Games(6);
        match.setPlayer2Games(6);

        TieBreakStrategy strategy = new TieBreakStrategy(match);
        strategy.addPointToFirstPlayer();

        assertEquals(1, match.getPlayer1Points());
    }

    @Test
    void advantageStartAt40_40 () {
        Player player = new Player(1L, "LOh");
        Player player2 = new Player(2L, "LOh2");
        var match = new MatchScoreModel(player, player2);

        match.setPlayer1Points(40);
        match.setPlayer2Points(40);

        var advantageStrategy = new AdvantageStrategy(match);
        advantageStrategy.addPointToFirstPlayer();

        assertEquals(0, match.getPlayer1Games());
        assertEquals(50, match.getPlayer1Points());
    }

    @Test
    void testTieBreakScoring() {
        Player player = new Player(1L, "LOh");
        Player player2 = new Player(2L, "LOh2");
        MatchScoreModel match = new MatchScoreModel(player, player2);
        match.setPlayer1Games(6);
        match.setPlayer2Games(6);

        TieBreakStrategy strategy = new TieBreakStrategy(match);
        strategy.addPointToFirstPlayer();
        strategy.addPointToFirstPlayer();

        assertEquals(2, match.getPlayer1Points());
        assertEquals(0, match.getPlayer2Points());
    }
}
