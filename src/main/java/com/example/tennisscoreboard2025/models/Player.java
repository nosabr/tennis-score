package com.example.tennisscoreboard2025.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PLAYERS")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = ("NAME"),nullable = false, unique = true)
    private String name;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }



    // getters/setters
}
