package com.rrtyui.service.matchScoreCalculationService.util.strategy;

import com.rrtyui.dto.MatchScoreModel;

public class TieBreakStrategy extends BaseStrategy {
    private static final int ONE_SCORE = 1;

    public TieBreakStrategy(MatchScoreModel matchScoreModel) {
        super(matchScoreModel);
    }

    @Override
    public void addPointToFirstPlayer() {
        int player1Points = matchScoreModel.getPlayer1Points();
        matchScoreModel.setPlayer1Points(player1Points + ONE_SCORE);
        checkWinTieBreak();
    }

    @Override
    public void addPointToSecondPlayer() {
        int player2Points = matchScoreModel.getPlayer2Points();
        matchScoreModel.setPlayer2Points(player2Points + ONE_SCORE);
        checkWinTieBreak();
    }


}
