package com.rrtyui.service.util;

import com.rrtyui.dto.MatchScoreModel;
import lombok.Setter;

@Setter
public class Accountant {
    private static final String PLAYER_1_ID = "1";
    private static final String PLAYER_2_ID = "2";
    private ScoreCalculationStrategy scoreCalculationStrategy;

    public Accountant(MatchScoreModel matchScoreModel) {
        this.scoreCalculationStrategy = new UsualStrategy(matchScoreModel);
    }


    public void calculate(String playerWhoWin) {
        switch (playerWhoWin) {
            case PLAYER_1_ID -> scoreCalculationStrategy.addPointToFirstPlayer();
            case PLAYER_2_ID -> scoreCalculationStrategy.addPointToSecondPlayer();
        }

    }
}
