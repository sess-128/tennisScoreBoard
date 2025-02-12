package com.rrtyui.service.util;

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
}
