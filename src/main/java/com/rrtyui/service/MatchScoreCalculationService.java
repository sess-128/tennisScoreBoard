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
import org.hibernate.SessionFactory;


@AllArgsConstructor
public class MatchScoreCalculationService {
    private final MatchScoreModel matchScoreModel;

    public void addPointToPlayer(String playerId) {
        MatchStateChecker matchStateChecker = new MatchStateChecker(matchScoreModel);
        StrategyFactory strategyFactory = new StrategyFactory(matchStateChecker);
        Strategy strategy = strategyFactory.getStrategy(matchScoreModel);

        Accountant accountant = new Accountant(matchScoreModel, strategy);
        accountant.addPoint(playerId);
        if (!matchStateChecker.isContinue()) {
            determineWinner();
            saveMatchFormCalc();
        }
    }


    @Transactional
    public void saveMatchFormCalc() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = HibernateUtil.getSession(sessionFactory)) {

            FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance(session);
            finishedMatchesPersistenceService.saveMatch(matchScoreModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void determineWinner() {
        if (matchScoreModel.getPlayer1Sets() == 2) {
            matchScoreModel.setWinner(matchScoreModel.getPlayer1());
        } else {
            matchScoreModel.setWinner(matchScoreModel.getPlayer2());
        }
    }


}
