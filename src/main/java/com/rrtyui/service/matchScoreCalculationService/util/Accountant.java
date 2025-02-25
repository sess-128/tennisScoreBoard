package com.rrtyui.service.matchScoreCalculationService.util;

import com.rrtyui.service.matchScoreCalculationService.util.strategy.Strategy;
import lombok.Setter;

@Setter
public class Accountant {
    private static final String PLAYER_1_ID = "1";
    private static final String PLAYER_2_ID = "2";
    private final Strategy strategy;

    public Accountant(Strategy strategy) {
        this.strategy = strategy;
    }

    public void addPoint(String playerId) {
        switch (playerId) {
            case PLAYER_1_ID -> strategy.addPointToFirstPlayer();
            case PLAYER_2_ID -> strategy.addPointToSecondPlayer();
        }

    }
}
