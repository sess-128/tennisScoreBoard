package com.rrtyui.service.matchScoreCalculationService.util.strategy;

import com.rrtyui.dto.MatchScoreModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract  class BaseStrategy implements Strategy {
    protected final MatchScoreModel matchScoreModel;

    protected void incrementPlayer1Games() {
        matchScoreModel.setPlayer1Games(matchScoreModel.getPlayer1Games() + 1);
    }

    protected void incrementPlayer2Games() {
        matchScoreModel.setPlayer2Games(matchScoreModel.getPlayer2Games() + 1);
    }

    protected void incrementPlayer1Sets() {
        matchScoreModel.setPlayer1Sets(matchScoreModel.getPlayer1Sets() + 1);
    }

    protected void incrementPlayer2Sets() {
        matchScoreModel.setPlayer2Sets(matchScoreModel.getPlayer2Sets() + 1);
    }

    protected void resetPoints() {
        matchScoreModel.setPlayer1Points(0);
        matchScoreModel.setPlayer2Points(0);
    }

    protected void resetGames() {
        matchScoreModel.setPlayer1Games(0);
        matchScoreModel.setPlayer2Games(0);
    }

    protected void resetToDeuce() {
        matchScoreModel.setPlayer1Points(40);
        matchScoreModel.setPlayer2Points(40);
    }

    protected boolean isPlayer1AD() {
        return matchScoreModel.getPlayer1Points() == 50;
    }

    protected boolean isPlayer2AD() {
        return matchScoreModel.getPlayer2Points() == 50;
    }

    protected void checkWinSet () {
        int player1Games = matchScoreModel.getPlayer1Games();
        int player2Games = matchScoreModel.getPlayer2Games();

        if (player1Games > 6 && (player1Games - player2Games) >= 2) {
            incrementPlayer1Sets();
            resetGames();
            resetPoints();
        }

        else if (player2Games > 6 && (player2Games - player1Games) >= 2) {
            incrementPlayer2Sets();
            resetGames();
            resetPoints();
        }
    }

    protected void checkWinTieBreak() {
        int player1Points = matchScoreModel.getPlayer1Points();
        int player2Points = matchScoreModel.getPlayer2Points();

        if (player1Points >= 7 && (player1Points - player2Points) >= 2) {
            incrementPlayer1Sets();
            resetGames();
            resetPoints();
        }

        else if (player2Points >= 7 && (player2Points - player1Points) >= 2) {
            incrementPlayer2Sets();
            resetGames();
            resetPoints();
        }
    }
}
