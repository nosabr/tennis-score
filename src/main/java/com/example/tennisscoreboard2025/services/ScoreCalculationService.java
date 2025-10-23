package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.models.Score;
import com.example.tennisscoreboard2025.models.scoreUtil.Pair;

public class ScoreCalculationService {
    public void addPointToPlayer(Score score, int scoredPlayerNumber) {
        //Score score = match.getScore();
        int firstPlayerPoints = score.getFirstPlayerPoints();
        int secondPlayerPoints = score.getSecondPlayerPoints();
        if(scoredPlayerNumber == 1){
            score.setFirstPlayerPoints(getNextPointValue(firstPlayerPoints, score.getGameMode()));
        } else {
            score.setSecondPlayerPoints(getNextPointValue(secondPlayerPoints, score.getGameMode()));
        }
        checkGameState(score);
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
        } else if(gameMode == 2 || gameMode == 3){
            return playerPoints + 1;
        }
        return 0;
    }

    private void checkGameState(Score score) {
        if(score.getGameMode() == 1){
            if (score.getFirstPlayerPoints() > 40 && score.getSecondPlayerPoints() < 40) {
                addGamePointToSet(score, 1);
                resetPoints(score);
            } else if (score.getSecondPlayerPoints() > 40 && score.getFirstPlayerPoints() < 40) {
                addGamePointToSet(score, 2);
                resetPoints(score);
            } else if(score.getFirstPlayerPoints() == 40 && score.getSecondPlayerPoints() == 40) {
                score.setGameMode(2); // Больше меньше
                resetPoints(score);
            }
            checkSetsPoints(score);
        } else if(score.getGameMode() == 2) {
            if (score.getFirstPlayerPoints() - score.getSecondPlayerPoints() == 2) {
                addGamePointToSet(score, 1);
                resetPoints(score);
                score.setGameMode(1);
            } else if (score.getSecondPlayerPoints() - score.getFirstPlayerPoints() == 2) {
                addGamePointToSet(score, 2);
                resetPoints(score);
                score.setGameMode(1);
            } else if (score.getFirstPlayerPoints() == 1 && score.getSecondPlayerPoints() == 1) {
                resetPoints(score);
            }
            checkSetsPoints(score);
        } else if(score.getGameMode() == 3) {
            if(score.getFirstPlayerPoints() - score.getSecondPlayerPoints() >= 2
                    && score.getFirstPlayerPoints() > 6) {
                addGamePointToSet(score, 1);
                resetPoints(score);
                score.setGameMode(1);
                score.setCurrentSet(score.getCurrentSet() + 1);
            } else if(score.getSecondPlayerPoints() - score.getFirstPlayerPoints() >= 2
                    && score.getSecondPlayerPoints() > 6) {
                addGamePointToSet(score, 2);
                resetPoints(score);
                score.setGameMode(1);
                score.setCurrentSet(score.getCurrentSet() + 1);
            }
        }
    }

    private void checkSetsPoints(Score score) {
        int currentSet = score.getCurrentSet();
        Pair[] sets = score.getSets();
        int firstPlayerSetPoints = sets[currentSet].getFirst();
        int secondPlayerSetPoints = sets[currentSet].getSecond();
        if(firstPlayerSetPoints - secondPlayerSetPoints >= 2 && firstPlayerSetPoints >= 6){
            resetPoints(score);
            score.setCurrentSet(currentSet + 1);
        } else if (secondPlayerSetPoints - firstPlayerSetPoints >= 2 && secondPlayerSetPoints >= 6) {
            resetPoints(score);
            score.setCurrentSet(currentSet + 1);
        } else if (firstPlayerSetPoints == 6 && secondPlayerSetPoints == 6) {
            resetPoints(score);
            score.setGameMode(3);
        }
    }

    private void resetPoints(Score score) {
        score.setFirstPlayerPoints(0);
        score.setSecondPlayerPoints(0);
    }

}
