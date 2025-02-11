package com.rrtyui.service.util;

import com.rrtyui.dto.MatchScoreModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsualStrategy implements ScoreCalculationStrategy {
    private MatchScoreModel matchScoreModel;
    private static final int[] POINTS = {0, 15, 30, 40};

    @Override
    public void addPointToFirstPlayer() {
        int currentPoints = matchScoreModel.getPlayer1Points();
        switch (currentPoints) {
            case 0 -> matchScoreModel.setPlayer1Points(POINTS[1]);
            case 15 -> matchScoreModel.setPlayer1Points(POINTS[2]);
            case 30 -> matchScoreModel.setPlayer1Points(POINTS[3]);
            case 40 -> {
                incrementPlayer1Games();
                startNewGames();
            }
        }
    }

    @Override
    public void addPointToSecondPlayer() {
        int currentPoints = matchScoreModel.getPlayer2Points();
        switch (currentPoints) {
            case 0 -> matchScoreModel.setPlayer2Points(POINTS[1]);
            case 15 -> matchScoreModel.setPlayer2Points(POINTS[2]);
            case 30 -> matchScoreModel.setPlayer2Points(POINTS[3]);
            case 40 -> {
                incrementPlayer2Games();
                startNewGames();
            }
        }
    }


    private void incrementPlayer1Sets() {
        int player1Sets = matchScoreModel.getPlayer1Sets();
        matchScoreModel.setPlayer1Sets(player1Sets + 1);
    }

    private void incrementPlayer2Sets() {
        int player2Sets = matchScoreModel.getPlayer2Sets();
        matchScoreModel.setPlayer2Sets(player2Sets + 1);
    }

    private void startNewGames() {
        matchScoreModel.setPlayer1Points(0);
        matchScoreModel.setPlayer2Points(0);
    }

    private void startNewSets() {
        matchScoreModel.setPlayer1Games(0);
        matchScoreModel.setPlayer2Games(0);
    }

    private void incrementPlayer1Games() {
        int player1Games = matchScoreModel.getPlayer1Games();
        int player2Games = matchScoreModel.getPlayer2Games();

        if (player1Games >= 6 && (player1Games - player2Games) >= 2) {
            incrementPlayer1Sets();
            startNewSets();
            return;
        }
        matchScoreModel.setPlayer1Games(player1Games + 1);
    }

    private void incrementPlayer2Games() {
        int player2Games = matchScoreModel.getPlayer2Games();
        int player1Games = matchScoreModel.getPlayer1Games();

        if (player2Games >= 6 && (player2Games - player1Games) >= 2) {
            incrementPlayer2Sets();
            startNewSets();
            return;
        }
        matchScoreModel.setPlayer2Games(player2Games + 1);
    }
}