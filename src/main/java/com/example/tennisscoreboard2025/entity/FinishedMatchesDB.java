package com.example.tennisscoreboard2025.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class FinishedMatchesDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // первый игрок
    @ManyToOne
    @JoinColumn(name = "player1_id", nullable = false)
    private int player1ID;

    // второй игрок
    @ManyToOne
    @JoinColumn(name = "player2_id", nullable = false)
    private int player2ID;

    // победитель (может быть null)
    @ManyToOne
    @JoinColumn(name = "winner_id")
    private int winnerID;
}
