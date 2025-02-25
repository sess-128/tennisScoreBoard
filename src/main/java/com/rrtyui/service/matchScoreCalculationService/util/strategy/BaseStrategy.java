package com.rrtyui.service.matchScoreCalculationService.util.strategy;

import com.rrtyui.dto.MatchScoreModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseStrategy implements Strategy {
    private static final int ONE_SCORE = 1;
    private static final int RESET_SCORE = 0;
    private static final int DEUCE_POINTS = 40;
    private static final int ADVANTAGE_POINTS = 50;
    private static final int WIN_DIFFERENCE_SCORE = 2;
    private static final int WIN_GAMES_POINTS = 6;
    private static final int WIN_TIEBREAK_POINTS = 7;

    protected final MatchScoreModel matchScoreModel;

    protected void incrementPlayer1Games() {
        matchScoreModel.setPlayer1Games(matchScoreModel.getPlayer1Games() + ONE_SCORE);
    }

    protected void incrementPlayer2Games() {
        matchScoreModel.setPlayer2Games(matchScoreModel.getPlayer2Games() + ONE_SCORE);
    }

    protected void incrementPlayer1Sets() {
        matchScoreModel.setPlayer1Sets(matchScoreModel.getPlayer1Sets() + ONE_SCORE);
    }

    protected void incrementPlayer2Sets() {
        matchScoreModel.setPlayer2Sets(matchScoreModel.getPlayer2Sets() + ONE_SCORE);
    }

    protected void resetPoints() {
        matchScoreModel.setPlayer1Points(RESET_SCORE);
        matchScoreModel.setPlayer2Points(RESET_SCORE);
    }

    protected void resetGames() {
        matchScoreModel.setPlayer1Games(RESET_SCORE);
        matchScoreModel.setPlayer2Games(RESET_SCORE);
    }

    protected void resetToDeuce() {
        matchScoreModel.setPlayer1Points(DEUCE_POINTS);
        matchScoreModel.setPlayer2Points(DEUCE_POINTS);
    }

    protected boolean isPlayer1AD() {
        return matchScoreModel.getPlayer1Points() == ADVANTAGE_POINTS;
    }

    protected boolean isPlayer2AD() {
        return matchScoreModel.getPlayer2Points() == ADVANTAGE_POINTS;
    }

    protected void checkWinSet() {
        int player1Games = matchScoreModel.getPlayer1Games();
        int player2Games = matchScoreModel.getPlayer2Games();

        if (player1Games > WIN_GAMES_POINTS && (player1Games - player2Games) >= WIN_DIFFERENCE_SCORE) {
            incrementPlayer1Sets();
            resetGames();
            resetPoints();
        } else if (player2Games > WIN_GAMES_POINTS && (player2Games - player1Games) >= WIN_DIFFERENCE_SCORE) {
            incrementPlayer2Sets();
            resetGames();
            resetPoints();
        }
    }

    protected void checkWinTieBreak() {
        int player1Points = matchScoreModel.getPlayer1Points();
        int player2Points = matchScoreModel.getPlayer2Points();

        if (player1Points >= WIN_TIEBREAK_POINTS && (player1Points - player2Points) >= WIN_DIFFERENCE_SCORE) {
            incrementPlayer1Sets();
            resetGames();
            resetPoints();
        } else if (player2Points >= WIN_TIEBREAK_POINTS && (player2Points - player1Points) >= WIN_DIFFERENCE_SCORE) {
            incrementPlayer2Sets();
            resetGames();
            resetPoints();
        }
    }
}
