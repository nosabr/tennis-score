package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.models.Score;
import com.example.tennisscoreboard2025.models.scoreUtil.Pair;

public class ScoreService {
    public void addPointToPlayer(Match match, int scoredPlayerNumber) {
        Score score = match.getScore();
        int firstPlayerPoints = score.getFirstPlayerPoints();
        int secondPlayerPoints = score.getSecondPlayerPoints();

        if(scoredPlayerNumber == 1){
            if(isGameWon(firstPlayerPoints, secondPlayerPoints)){
                addGamePointToSet(score, scoredPlayerNumber);
                resetPoints(score);
            } else {
                int nextPointValue = getNextPointValue(firstPlayerPoints, score.getGameMode());
                score.setFirstPlayerPoints(nextPointValue);
            }
        }  else {
            if(isGameWon(secondPlayerPoints, firstPlayerPoints)){
                addGamePointToSet(score, scoredPlayerNumber);
                resetPoints(score);
            } else {
                int nextPointValue = getNextPointValue(secondPlayerPoints, score.getGameMode());
                score.setSecondPlayerPoints(nextPointValue);
            }
        }

    }

    private boolean isGameWon(int player1,  int player2) {
        return player1 == 40 && player2 < 40;
    }

    private void addGamePointToSet(Score score, int scoredPlayerNumber) {
        Pair[] sets = score.getSets();
        int currentSet = score.getCurrentSet();

        if(scoredPlayerNumber == 1){
            sets[currentSet].setFirst(sets[currentSet].getFirst() + 1);
        } else {
            sets[currentSet].setSecond(sets[currentSet].getSecond() + 1);
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
            if(firstPlayerPoints == 40 && secondPlayerPoints == 40) { // проверка на больше меньше
                score.setGameMode(2);
                resetPoints(score);
            } else if(firstPlayerPoints == 40 && secondPlayerPoints < 30) {}
        } else if(score.getGameMode() == 2){

        } else if(score.getGameMode() == 3){

        }

    }


    private void resetPoints(Score score) {
        score.setFirstPlayerPoints(0);
        score.setSecondPlayerPoints(0);
    }

}
