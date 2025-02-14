package com.rrtyui.service;

import com.rrtyui.repository.MatchRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FinishedMatchesPersistenceService {
    //TODO тут будет работа с матч-репозиторием - хранить, получать и добавлять матчи
    private final MatchRepository matchRepository;

}
