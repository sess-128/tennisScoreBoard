package com.rrtyui.dto;

import lombok.Getter;

import java.util.UUID;


@Getter
public class MatchModel {
    private final UUID id;

    public MatchModel(MatchScoreModel matchScoreModel) {
        this.id = UUID.randomUUID();
    }
}
