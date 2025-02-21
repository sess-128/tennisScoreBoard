package com.rrtyui.dto;

import com.rrtyui.entity.Match;
import lombok.Builder;

import java.util.List;

@Builder
public record MatchPageResponseDto(List<Match> matches,
                                   int currentPage,
                                   int totalPages) {
}
