package com.rrtyui.service;

import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.service.util.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchScoreCalculationService {
    private final MatchScoreModel matchScoreModel;

    public void addPointToPlayer(String playerId) {
        Accountant accountant = new Accountant(matchScoreModel);
        accountant.setStrategy(getStrategy());
        accountant.addPoint(playerId);


    }

    private Strategy getStrategy() {
        if (isTieBreak()) {
            return new TieBreakStrategy(matchScoreModel);
        } else if (isDeuce() || isAdvantage()) {
            return new AdvantageStrategy(matchScoreModel);
        } else {
            return new UsualStrategy(matchScoreModel);
        }
    }

    private boolean isDeuce() {
        return matchScoreModel.getPlayer1Points() == 40 && matchScoreModel.getPlayer2Points() == 40;
    }

    private boolean isAdvantage() {
        return matchScoreModel.getPlayer1Points() == 50 || matchScoreModel.getPlayer2Points() == 50;
    }

    private boolean isTieBreak() {
        return matchScoreModel.getPlayer1Games() == 6 && matchScoreModel.getPlayer2Games() == 6;
    }
}
