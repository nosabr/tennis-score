package com.example.tennisscoreboard2025.models;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = ("name"),nullable = false, unique = true)
    private String name;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }



    // getters/setters
}
