package com.example.tennisscoreboard2025.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class FinishedMatches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "player1_id", nullable = false)
    private Player player1;

    @ManyToOne @JoinColumn(name = "player2_id", nullable = false)
    private Player player2;

    @ManyToOne @JoinColumn(name = "winner_id", nullable = false)
    private Player winner;
}
