package com.rrtyui.service;

import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.service.matchScoreCalculationService.util.Accountant;
import com.rrtyui.service.matchScoreCalculationService.util.MatchStateChecker;
import com.rrtyui.service.matchScoreCalculationService.util.StrategyFactory;
import com.rrtyui.service.matchScoreCalculationService.util.strategy.Strategy;
import com.rrtyui.util.HibernateUtil;
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

        if (matchStateChecker.isMatchEnded()) {
            determineWinner();
            saveMatchFormCalc();
            return;
        }

        Accountant accountant = new Accountant(matchScoreModel, strategy);
        accountant.addPoint(playerId);
    }

    public boolean isContinue() {
        return matchScoreModel.getPlayer1Sets() < 2 && matchScoreModel.getPlayer2Sets() < 2;
    }

    public void saveMatchFormCalc() {
        System.out.println("Start saving match...");
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = HibernateUtil.getSession(sessionFactory)) {

            System.out.println("Сохранение матча: " + matchScoreModel);
            FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance(session);
            finishedMatchesPersistenceService.saveMatch(matchScoreModel);
            System.out.println("Матч успешно сохранен.");
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении матча: " + e.getMessage());
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
