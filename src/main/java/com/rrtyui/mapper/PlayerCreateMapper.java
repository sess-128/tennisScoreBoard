package com.rrtyui.mapper;

import com.rrtyui.dto.PlayerCreateDto;
import com.rrtyui.entity.Player;

public class PlayerCreateMapper implements Mapper<PlayerCreateDto, Player> {
    @Override
    public Player mapFrom(PlayerCreateDto object) {
        return Player.builder()
                .name(object.name())
                .build();
    }
}
