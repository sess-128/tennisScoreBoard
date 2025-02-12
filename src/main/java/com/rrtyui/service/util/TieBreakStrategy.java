package com.rrtyui.service.util;

import com.rrtyui.dto.MatchScoreModel;

public class TieBreakStrategy extends BaseStrategy{
    public TieBreakStrategy(MatchScoreModel matchScoreModel) {
        super(matchScoreModel);
    }

    @Override
    public void addPointToFirstPlayer() {
        int player1Points = matchScoreModel.getPlayer1Points();
        matchScoreModel.setPlayer1Points(player1Points + 1);
        checkWin();
    }

    @Override
    public void addPointToSecondPlayer() {
        int player2Points = matchScoreModel.getPlayer2Points();
        matchScoreModel.setPlayer2Points(player2Points + 1);
        checkWin();
    }

    private void checkWin() {
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
