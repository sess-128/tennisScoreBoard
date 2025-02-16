package com.rrtyui.service.matchScoreCalculationService.util.strategy;

import com.rrtyui.dto.MatchScoreModel;

public class TieBreakStrategy extends BaseStrategy{
    public TieBreakStrategy(MatchScoreModel matchScoreModel) {
        super(matchScoreModel);
    }

    @Override
    public void addPointToFirstPlayer() {
        int player1Points = matchScoreModel.getPlayer1Points();
        matchScoreModel.setPlayer1Points(player1Points + 1);
        checkWinTieBreak();
    }

    @Override
    public void addPointToSecondPlayer() {
        int player2Points = matchScoreModel.getPlayer2Points();
        matchScoreModel.setPlayer2Points(player2Points + 1);
        checkWinTieBreak();
    }


}
