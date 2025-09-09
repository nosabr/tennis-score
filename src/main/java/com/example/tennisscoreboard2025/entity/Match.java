package com.example.tennisscoreboard2025.entity;

import java.util.UUID;

public class Match {
    private int player1ID;
    private int player2ID;
    private UUID uuid;

    public int getPlayer2ID() {
        return player2ID;
    }

    public int getPlayer1ID() {
        return player1ID;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setPlayer1ID(int player1ID) {
        this.player1ID = player1ID;
    }

    public void setPlayer2ID(int player2ID) {
        this.player2ID = player2ID;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
