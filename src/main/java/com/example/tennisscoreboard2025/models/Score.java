package com.example.tennisscoreboard2025.models;

import com.example.tennisscoreboard2025.models.scoreUtil.Pair;
import lombok.Data;

@Data
public class Score {
    Pair[] sets;
    private int currentSet = 0;
    private int gameMode = 1; // 1 - Game; 2 - ADV; 3 - tiebreak;
    private int firstPlayerPoints = 0;
    private int secondPlayerPoints = 0;

    public Score() {
        fillSets();
    }

    private void fillSets() {
        sets = new Pair[3]; // три сета
        for (int i = 0; i < sets.length; i++) {
            sets[i] = new Pair(0, 0);
        }
        currentSet = 0;
    }
}
