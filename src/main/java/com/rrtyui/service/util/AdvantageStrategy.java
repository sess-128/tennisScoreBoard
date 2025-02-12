package com.rrtyui.service.util;

import com.rrtyui.dto.MatchScoreModel;

public class AdvantageStrategy extends BaseStrategy {
    public AdvantageStrategy(MatchScoreModel matchScoreModel) {
        super(matchScoreModel);
    }

    @Override
    public void addPointToFirstPlayer() {
        if (isPlayer1AD()) {
            incrementPlayer1Games();
            resetPoints();
        } else if (isPlayer2AD()) {
            resetToDeuce();
        } else {
            matchScoreModel.setPlayer1Points(50);
        }
    }

    @Override
    public void addPointToSecondPlayer() {
        if (isPlayer2AD()) {
            incrementPlayer2Games();
            resetPoints();
        } else if (isPlayer1AD()) {
            resetToDeuce();
        } else {
            matchScoreModel.setPlayer2Points(50);
        }
    }

}