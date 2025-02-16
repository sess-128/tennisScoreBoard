package com.rrtyui.mapper;

import com.rrtyui.dto.MatchResponseDto;
import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.entity.Match;

public class FinishedMatchCreateMapper implements Mapper<MatchScoreModel, Match>{
    @Override
    public Match mapFrom(MatchScoreModel object) {
        return Match.builder()
                .player1(object.getPlayer1())
                .player2(object.getPlayer2())
                .winner(object.getWinner())
                .build();
    }
}
