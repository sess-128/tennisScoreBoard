package com.rrtyui.service.util;

import com.rrtyui.dto.MatchScoreModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdvantageStrategy implements ScoreCalculationStrategy {
    private MatchScoreModel matchScoreModel;

    @Override
    public void addPointToFirstPlayer() {
        if (isDeuce()) {
            handleAdvantage(true); // Обработка преимущества для первого игрока
        } else {
            incrementPlayer1Points(); // Обычное увеличение очков
        }
    }

    @Override
    public void addPointToSecondPlayer() {
        if (isDeuce()) {
            handleAdvantage(false); // Обработка преимущества для второго игрока
        } else {
            incrementPlayer2Points(); // Обычное увеличение очков
        }
    }

    private boolean isDeuce() {
        return matchScoreModel.getPlayer1Points() == 40 && matchScoreModel.getPlayer2Points() == 40;
    }

    private void handleAdvantage(boolean isPlayer1) {
        if (isPlayer1) {
            if (matchScoreModel.isPlayer1Advantage()) {
                // Игрок 1 выигрывает гейм
                incrementPlayer1Games();
                resetPoints();
            } else if (matchScoreModel.isPlayer2Advantage()) {
                // Счёт возвращается к деюсу
                matchScoreModel.setPlayer2Advantage(false);
            } else {
                // Игрок 1 получает преимущество
                matchScoreModel.setPlayer1Advantage(true);
            }
        } else {
            if (matchScoreModel.isPlayer2Advantage()) {
                // Игрок 2 выигрывает гейм
                incrementPlayer2Games();
                resetPoints();
            } else if (matchScoreModel.isPlayer1Advantage()) {
                // Счёт возвращается к деюсу
                matchScoreModel.setPlayer1Advantage(false);
            } else {
                // Игрок 2 получает преимущество
                matchScoreModel.setPlayer2Advantage(true);
            }
        }
    }

    private void incrementPlayer1Points() {
        int player1Points = matchScoreModel.getPlayer1Points();
        if (player1Points == 0) {
            matchScoreModel.setPlayer1Points(15);
        } else if (player1Points == 15) {
            matchScoreModel.setPlayer1Points(30);
        } else if (player1Points == 30) {
            matchScoreModel.setPlayer1Points(40);
        } else if (player1Points == 40) {
            // Переход к деюсу или завершение гейма
            if (matchScoreModel.getPlayer2Points() == 40) {
                // Деюс
                matchScoreModel.setPlayer1Points(40);
            } else {
                // Игрок 1 выигрывает гейм
                incrementPlayer1Games();
                resetPoints();
            }
        }
    }

    private void incrementPlayer2Points() {
        int player2Points = matchScoreModel.getPlayer2Points();
        if (player2Points == 0) {
            matchScoreModel.setPlayer2Points(15);
        } else if (player2Points == 15) {
            matchScoreModel.setPlayer2Points(30);
        } else if (player2Points == 30) {
            matchScoreModel.setPlayer2Points(40);
        } else if (player2Points == 40) {
            // Переход к деюсу или завершение гейма
            if (matchScoreModel.getPlayer1Points() == 40) {
                // Деюс
                matchScoreModel.setPlayer2Points(40);
            } else {
                // Игрок 2 выигрывает гейм
                incrementPlayer2Games();
                resetPoints();
            }
        }
    }

    private void incrementPlayer1Games() {
        int player1Games = matchScoreModel.getPlayer1Games();
        matchScoreModel.setPlayer1Games(player1Games + 1);
    }

    private void incrementPlayer2Games() {
        int player2Games = matchScoreModel.getPlayer2Games();
        matchScoreModel.setPlayer2Games(player2Games + 1);
    }

    private void resetPoints() {
        matchScoreModel.setPlayer1Points(0);
        matchScoreModel.setPlayer2Points(0);
        matchScoreModel.setPlayer1Advantage(false);
        matchScoreModel.setPlayer2Advantage(false);
    }
}