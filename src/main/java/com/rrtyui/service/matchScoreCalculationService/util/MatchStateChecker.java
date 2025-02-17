package com.rrtyui.service.matchScoreCalculationService.util;

import com.rrtyui.dto.MatchScoreModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchStateChecker {
    private final MatchScoreModel matchScoreModel;

    public boolean isContinue() {
        return matchScoreModel.getPlayer1Sets() < 2 && matchScoreModel.getPlayer2Sets() < 2;
    }

    public boolean isDeuce() {
        return matchScoreModel.getPlayer1Points() == 40 && matchScoreModel.getPlayer2Points() == 40;
    }

    public boolean isAdvantage() {
        return matchScoreModel.getPlayer1Points() == 50 || matchScoreModel.getPlayer2Points() == 50;
    }

    public boolean isTieBreak() {
        return matchScoreModel.getPlayer1Games() == 6 && matchScoreModel.getPlayer2Games() == 6;
    }
}
