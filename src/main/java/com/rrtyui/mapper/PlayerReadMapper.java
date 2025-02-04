package com.rrtyui.mapper;

import com.rrtyui.dto.PlayerDto;
import com.rrtyui.entity.Player;

public class PlayerReadMapper implements Mapper<Player, PlayerDto> {

    @Override
    public PlayerDto mapFrom(Player object) {
        return new PlayerDto(
                object.getId(),
                object.getName()
        );
    }
}
