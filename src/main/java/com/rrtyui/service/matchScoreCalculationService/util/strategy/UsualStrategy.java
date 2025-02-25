package com.rrtyui.service.matchScoreCalculationService.util.strategy;

import com.rrtyui.dto.MatchScoreModel;

public class UsualStrategy extends BaseStrategy {
    private static final int[] POINTS = {0, 15, 30, 40};

    public UsualStrategy(MatchScoreModel matchScoreModel) {
        super(matchScoreModel);
    }

    @Override
    public void addPointToFirstPlayer() {
        int currentPoints = matchScoreModel.getPlayer1Points();
        switch (currentPoints) {
            case 0 -> matchScoreModel.setPlayer1Points(POINTS[1]);
            case 15 -> matchScoreModel.setPlayer1Points(POINTS[2]);
            case 30 -> matchScoreModel.setPlayer1Points(POINTS[3]);
            case 40 -> {
                incrementPlayer1Games();
                resetPoints();
                checkWinSet();
            }
        }
    }

    @Override
    public void addPointToSecondPlayer() {
        int currentPoints = matchScoreModel.getPlayer2Points();
        switch (currentPoints) {
            case 0 -> matchScoreModel.setPlayer2Points(POINTS[1]);
            case 15 -> matchScoreModel.setPlayer2Points(POINTS[2]);
            case 30 -> matchScoreModel.setPlayer2Points(POINTS[3]);
            case 40 -> {
                incrementPlayer2Games();
                resetPoints();
                checkWinSet();
            }
        }
    }
}