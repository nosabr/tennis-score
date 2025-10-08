package com.example.tennisscoreboard2025.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MATCHES")
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne @JoinColumn(name = "player1_id", nullable = false)
    private Player player1;

    @ManyToOne @JoinColumn(name = "player2_id", nullable = false)
    private Player player2;

    @ManyToOne @JoinColumn(name = "winner_id", nullable = false)
    private Player winner;

    @Transient
    private Score score = new Score();

}
