package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.models.Score;

public class ScoreService {
    public void addPointToPlayer(Match match, int scoredPlayerNumber) {
        Score score = match.getScore();
        int firstPlayerPoints = score.getFirstPlayerPoints();
        int secondPlayerPoints = score.getSecondPlayerPoints();
        if(scoredPlayerNumber == 1){
            score.setFirstPlayerPoints(getNextPointValue(firstPlayerPoints,score.getGameMode()));
        } else {
            score.setSecondPlayerPoints(getNextPointValue(secondPlayerPoints,score.getGameMode()));
        }
    }

    private int getNextPointValue(int playerPoints, int gameMode) {
        if(gameMode == 1){
            if(playerPoints < 30){
                return playerPoints + 15;
            } else {
                return playerPoints + 10;
            }
        } else if(gameMode == 2){
            return playerPoints + 1;
        } else if(gameMode == 3){
            return playerPoints + 1;
        }
        return 0;
    }

    private void checkScoreState(Score score) {
        int firstPlayerPoints = score.getFirstPlayerPoints();
        int secondPlayerPoints = score.getSecondPlayerPoints();
        if(score.getGameMode() == 1){
            
        } else if(score.getGameMode() == 2){

        } else if(score.getGameMode() == 3){

        }

    }


    private void resetPoints(Score score) {
        score.setFirstPlayerPoints(0);
        score.setSecondPlayerPoints(0);
    }

}
