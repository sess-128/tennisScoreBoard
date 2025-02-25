package com.rrtyui.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_1")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player_2")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner")
    private Player winner;
}
