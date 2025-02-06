package com.rrtyui.dto;

import lombok.Getter;

import java.util.UUID;


public class MatchModel {
    @Getter
    private final UUID id;
    private final MatchScoreModel matchScoreModel;

    public MatchModel(MatchScoreModel matchScoreModel) {
        this.id = UUID.randomUUID();
        this.matchScoreModel = matchScoreModel;
    }

}
