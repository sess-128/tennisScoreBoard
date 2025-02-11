package com.rrtyui.service;

import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.service.util.Accountant;
import com.rrtyui.service.util.AdvantageStrategy;
import com.rrtyui.service.util.TieBreakStrategy;
import com.rrtyui.service.util.UsualStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchScoreCalculationService {
    private final MatchScoreModel matchScoreModel;

    public void addPoint(String playerWhoWinPoint) {
        Accountant accountant = new Accountant(matchScoreModel);
        accountant.setScoreCalculationStrategy(new UsualStrategy(matchScoreModel));
        if (isDeuce()) {
            accountant.setScoreCalculationStrategy(new AdvantageStrategy(matchScoreModel));
        }
        if (isTieBreak()) {
            accountant.setScoreCalculationStrategy(new TieBreakStrategy());
        }
        accountant.calculate(playerWhoWinPoint);
    }

    private boolean isDeuce() {
        return matchScoreModel.getPlayer1Points() == 40 && matchScoreModel.getPlayer2Points() == 40;

    }
    private boolean isTieBreak() {
        return matchScoreModel.getPlayer1Games() == 6 && matchScoreModel.getPlayer2Games() == 6;
    }



}
