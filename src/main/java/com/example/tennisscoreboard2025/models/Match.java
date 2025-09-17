package com.example.tennisscoreboard2025.models;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class Match {

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "player1_id", nullable = false)
    private Player player1;

    @ManyToOne @JoinColumn(name = "player2_id", nullable = false)
    private Player player2;

    @ManyToOne @JoinColumn(name = "winner_id", nullable = false)
    private Player winner;

    @Transient
    private Score score;

    public Long getId() { return id;}

    public Player getPlayer1() { return player1;}

    public Player getPlayer2() { return player2;}

    public Player getWinner() { return winner;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
