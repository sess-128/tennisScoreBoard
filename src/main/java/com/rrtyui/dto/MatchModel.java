package com.rrtyui.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MatchModel {
    private final UUID id;
    private final MatchScoreModel matchScoreModel;

    public MatchModel(MatchScoreModel matchScoreModel) {
        this.matchScoreModel = matchScoreModel;
        this.id = UUID.randomUUID();
    }
}
