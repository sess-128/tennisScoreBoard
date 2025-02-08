package com.rrtyui.controller;

import com.rrtyui.service.FinishedMatchesPersistenceService;
import com.rrtyui.service.MatchScoreCalculationService;
import com.rrtyui.service.OngoingMatchesService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchScoreController {
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private final MatchScoreCalculationService matchScoreCalculationService;
    private final OngoingMatchesService ongoingMatchesService;

    public Long createNewMatch(String player1, String player2) {
        return null;
    }

}
