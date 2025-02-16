package com.rrtyui.dto;

import com.rrtyui.entity.Player;
import lombok.Builder;

@Builder
public record MatchResponseDto (Player player1,
                                Player player2,
                                Player winner) {
}
