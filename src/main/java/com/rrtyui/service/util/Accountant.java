package com.rrtyui.service.util;

import com.rrtyui.dto.MatchScoreModel;
import lombok.Setter;

@Setter
public class Accountant {
    private static final String PLAYER_1_ID = "1";
    private static final String PLAYER_2_ID = "2";
    private Strategy strategy;

    public Accountant(MatchScoreModel matchScoreModel) {
        this.strategy = new UsualStrategy(matchScoreModel);
    }


    public void addPoint(String playerId) {
        switch (playerId) {
            case PLAYER_1_ID -> strategy.addPointToFirstPlayer();
            case PLAYER_2_ID -> strategy.addPointToSecondPlayer();
        }

    }
}
