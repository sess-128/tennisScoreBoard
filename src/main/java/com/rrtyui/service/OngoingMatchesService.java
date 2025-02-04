package com.rrtyui.service;

import com.rrtyui.dto.PlayerCreateDto;
import com.rrtyui.dto.PlayerDto;
import com.rrtyui.mapper.PlayerCreateMapper;
import com.rrtyui.mapper.PlayerReadMapper;
import com.rrtyui.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class OngoingMatchesService {
    private final PlayerRepository playerRepository;
    private final PlayerReadMapper playerReadMapper;
    private final PlayerCreateMapper playerCreateMapper;

    @Transactional
    public Long create(PlayerCreateDto playerCreateDto){
        // validation
        var playerEntity = playerCreateMapper.mapFrom(playerCreateDto);
        return playerRepository.save(playerEntity).getId();
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
