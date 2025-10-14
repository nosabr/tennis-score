package com.example.tennisscoreboard2025.models;


import lombok.Data;

@Data
public class Score {
    private int currentSet = 0;
    private int gameMode = 1; // 1 - Game; 2 - ADV; 3 - tiebreak;
    private int firstPlayerPoints = 0;
    private int secondPlayerPoints = 0;
}
