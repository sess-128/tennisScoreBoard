package com.rrtyui.dto;

import com.rrtyui.entity.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchScoreModel {
    private Player player1;
    private Player player2;

    private int player1Points;
    private int player1Games;
    private int player1Sets;

    private int player2Points;
    private int player2Games;
    private int player2Sets;


    public MatchScoreModel(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

}
