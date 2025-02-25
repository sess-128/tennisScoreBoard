package com.rrtyui.service.matchScoreCalculationService.util;

import com.rrtyui.dto.MatchScoreModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchStateChecker {
    private static final int DEUCE_POINTS = 40;
    private static final int ADVANTAGE_POINTS = 50;
    private static final int WIN_DIFFERENCE_SCORE = 2;
    private static final int WIN_GAMES_POINTS = 6;
    private final MatchScoreModel matchScoreModel;

    public boolean isContinue() {
        return matchScoreModel.getPlayer1Sets() < WIN_DIFFERENCE_SCORE && matchScoreModel.getPlayer2Sets() < WIN_DIFFERENCE_SCORE;
    }

    public boolean isDeuce() {
        return matchScoreModel.getPlayer1Points() == DEUCE_POINTS && matchScoreModel.getPlayer2Points() == DEUCE_POINTS;
    }

    public boolean isAdvantage() {
        return matchScoreModel.getPlayer1Points() == ADVANTAGE_POINTS || matchScoreModel.getPlayer2Points() == ADVANTAGE_POINTS;
    }

    public boolean isTieBreak() {
        return matchScoreModel.getPlayer1Games() == WIN_GAMES_POINTS && matchScoreModel.getPlayer2Games() == WIN_GAMES_POINTS;
    }
}
