package com.rrtyui.service;

import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.service.matchScoreCalculationService.util.Accountant;
import com.rrtyui.service.matchScoreCalculationService.util.MatchStateChecker;
import com.rrtyui.service.matchScoreCalculationService.util.StrategyFactory;
import com.rrtyui.service.matchScoreCalculationService.util.strategy.Strategy;
import com.rrtyui.util.HibernateUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Session;


@AllArgsConstructor
public class MatchScoreCalculationService {
    private static final int WIN_GAME_SETS = 2;
    private final MatchScoreModel matchScoreModel;

    public void addPointToPlayer(String playerId) {
        MatchStateChecker matchStateChecker = new MatchStateChecker(matchScoreModel);
        StrategyFactory strategyFactory = new StrategyFactory(matchStateChecker);
        Strategy strategy = strategyFactory.getStrategy(matchScoreModel);

        Accountant accountant = new Accountant(strategy);
        accountant.addPoint(playerId);
        if (!matchStateChecker.isContinue()) {
            determineWinner();
            saveMatchFormCalc();
        }
    }

    @Transactional
    public void saveMatchFormCalc() {
        Session session = HibernateUtil.getSession();

        FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance(session);
        finishedMatchesPersistenceService.saveMatch(matchScoreModel);
    }

    private void determineWinner() {
        if (matchScoreModel.getPlayer1Sets() == WIN_GAME_SETS) {
            matchScoreModel.setWinner(matchScoreModel.getPlayer1());
        } else {
            matchScoreModel.setWinner(matchScoreModel.getPlayer2());
        }
    }


}
