package com.rrtyui.controller;

import com.rrtyui.service.MatchScoreCalculationService;
import jakarta.servlet.annotation.WebServlet;
import lombok.AllArgsConstructor;

@WebServlet("/MatchScoreController")
@AllArgsConstructor
public class MatchScoreController {
    private final MatchScoreCalculationService matchScoreCalculationService;

    public Long createNewMatch(String player1, String player2) {
        return null;
    }

}
