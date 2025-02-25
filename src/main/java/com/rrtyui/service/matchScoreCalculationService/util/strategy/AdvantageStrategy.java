package com.rrtyui.service.matchScoreCalculationService.util.strategy;

import com.rrtyui.dto.MatchScoreModel;

public class AdvantageStrategy extends BaseStrategy {
    private static final int ADVANTAGE_POINTS = 50;

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
            matchScoreModel.setPlayer1Points(ADVANTAGE_POINTS);
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
            matchScoreModel.setPlayer2Points(ADVANTAGE_POINTS);
        }
    }

}