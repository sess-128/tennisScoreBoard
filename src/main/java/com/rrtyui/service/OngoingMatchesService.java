package com.rrtyui.service;

import com.rrtyui.dto.MatchModel;
import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.dto.PlayerCreateDto;
import com.rrtyui.dto.PlayerDto;
import com.rrtyui.entity.Player;
import com.rrtyui.mapper.PlayerCreateMapper;
import com.rrtyui.mapper.PlayerReadMapper;
import com.rrtyui.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class OngoingMatchesService {
    private final PlayerRepository playerRepository;
    private final PlayerReadMapper playerReadMapper;
    private final PlayerCreateMapper playerCreateMapper;

    private static final Map<UUID, MatchScoreModel> ongoingMatches = new ConcurrentHashMap<>();

    @Transactional
    public Long create(PlayerCreateDto playerCreateDto){
        // validation
        var playerEntity = playerCreateMapper.mapFrom(playerCreateDto);

        return playerRepository.save(playerEntity).getId();
    }

    @Transactional
    public UUID createMatch(PlayerCreateDto player1, PlayerCreateDto player2) {
        var player1Entity = playerCreateMapper.mapFrom(player1);
        var player2Entity = playerCreateMapper.mapFrom(player2);

        if (player1Entity == null || player2Entity == null) {
            throw new RuntimeException("Ошибка при преобразовании PlayerCreateDto");
        }

        Player save = playerRepository.save(player1Entity);
        Player save1 = playerRepository.save(player2Entity);

        // Убедитесь, что оба игрока успешно сохранены
        if (save == null || save1 == null) {
            throw new RuntimeException("Ошибка при сохранении игроков");
        }

        MatchScoreModel matchScoreModel = new MatchScoreModel(save, save1);
        MatchModel matchModel = new MatchModel(matchScoreModel);

        UUID id = matchModel.getId(); // Получаем уникальный идентификатор

        ongoingMatches.put(id, matchScoreModel);

        if (id == null) {
            throw new RuntimeException("UUID нету");
        }

        System.out.println("Created Match UUID: " + id);
        return id;
    }

    @Transactional
    public Optional<PlayerDto> findById(Long id) {
        return playerRepository.findById(id)
                .map(playerReadMapper::mapFrom);
    }

    @Transactional
    public boolean delete(Long id) {
        var maybePlayer = playerRepository.findById(id);
        maybePlayer.ifPresent(player -> playerRepository.delete(player.getId()));
        return maybePlayer.isPresent();
    }
}
