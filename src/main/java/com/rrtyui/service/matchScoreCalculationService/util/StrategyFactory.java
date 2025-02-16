package com.rrtyui.service.matchScoreCalculationService.util;

import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.service.matchScoreCalculationService.util.strategy.AdvantageStrategy;
import com.rrtyui.service.matchScoreCalculationService.util.strategy.Strategy;
import com.rrtyui.service.matchScoreCalculationService.util.strategy.TieBreakStrategy;
import com.rrtyui.service.matchScoreCalculationService.util.strategy.UsualStrategy;

public class StrategyFactory {
    private final MatchStateChecker stateChecker;

    public StrategyFactory(MatchStateChecker stateChecker) {
        this.stateChecker = stateChecker;
    }

    public Strategy getStrategy(MatchScoreModel matchScoreModel) {
        if (stateChecker.isTieBreak()) {
            return new TieBreakStrategy(matchScoreModel);
        } else if (stateChecker.isDeuce() || stateChecker.isAdvantage()) {
            return new AdvantageStrategy(matchScoreModel);
        } else {
            return new UsualStrategy(matchScoreModel);
        }
    }
}
