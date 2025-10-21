package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.models.Score;

public class ScoreService {
    int firstPlayerPoints;
    int secondPlayerPoints;
    Score score;
    public void addPointToPlayer(Match match, int scoredPlayerNumber) {
        score = match.getScore();
        firstPlayerPoints = score.getFirstPlayerPoints();
        secondPlayerPoints = score.getSecondPlayerPoints();
        if(scoredPlayerNumber == 1){
            score.setFirstPlayerPoints(firstPlayerPoints + 1);
        } else {
            score.setSecondPlayerPoints(secondPlayerPoints + 1);
        }
    }
}
