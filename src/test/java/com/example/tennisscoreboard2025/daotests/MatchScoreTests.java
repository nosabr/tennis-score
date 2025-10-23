package com.example.tennisscoreboard2025.daotests;

import com.example.tennisscoreboard2025.models.Score;
import com.example.tennisscoreboard2025.models.scoreUtil.Pair;
import com.example.tennisscoreboard2025.services.ScoreCalculationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchScoreTests{
    ScoreCalculationService scoreCalculationService = new ScoreCalculationService();

    @Test
    public void PointsAddTest(){
        Score score = new Score();
        addPoints(score,1,2);
        addPoints(score,2,2); // 30 30
        assertEquals(score.getFirstPlayerPoints(),score.getSecondPlayerPoints());
        assertEquals(30,score.getFirstPlayerPoints());
        assertEquals(30, score.getSecondPlayerPoints());
        addPoints(score,1,1);
        addPoints(score,2,1); // ADV MODE 0 0
        assertEquals(0,score.getFirstPlayerPoints());
        assertEquals(0, score.getSecondPlayerPoints());
        addPoints(score,1,1);
        addPoints(score,2,1); // 0 0
        assertEquals(0,score.getFirstPlayerPoints());
        assertEquals(0, score.getSecondPlayerPoints());
        addPoints(score,2,2); // 2 PLAYER WINS GAME
        assertEquals(0,score.getFirstPlayerPoints());
        assertEquals(0,score.getSecondPlayerPoints());
        assertEquals(0,score.getCurrentSet());
        assertEquals(1, score.getSets()[0].getSecond());
    }

    @Test
    public void TieBreakTest2(){
        Score score = new Score();
        for(int i = 0; i < 5; i++){
            addPoints(score,1,4);
        }
        for(int i = 0; i < 5; i++){
            addPoints(score,2,4);
        }
        assertEquals(5,score.getSets()[0].getFirst());
        assertEquals(5,score.getSets()[0].getSecond());
        addPoints(score,1,4);
        addPoints(score,2,4);
        assertEquals(6,score.getSets()[0].getFirst());
        assertEquals(6,score.getSets()[0].getSecond());
        assertEquals(3,score.getGameMode());
        addPoints(score,1,6);
        addPoints(score,2,6);
        assertEquals(6,score.getFirstPlayerPoints());
        assertEquals(6,score.getSecondPlayerPoints());
        addPoints(score,1,2);// сет закончен победой 8 6 первым игроком
        assertEquals(7,score.getSets()[0].getFirst());
        assertEquals(6,score.getSets()[0].getSecond()); // проверка первого счета первого сета
        assertEquals(0,score.getFirstPlayerPoints());
        assertEquals(0,score.getSecondPlayerPoints());
        assertEquals(1, score.getCurrentSet());
    }

    @Test
    public void TieBreakTest(){
        Score score = new Score();
        for(int i = 0; i < 5; i++){
            addPoints(score,1,4);
        }
        for(int i = 0; i < 5; i++){
            addPoints(score,2,4);
        }
        assertEquals(5,score.getSets()[0].getFirst());
        assertEquals(5,score.getSets()[0].getSecond());
        addPoints(score,1,4);
        addPoints(score,2,4);
        assertEquals(6,score.getSets()[0].getFirst());
        assertEquals(6,score.getSets()[0].getSecond());
        assertEquals(3,score.getGameMode());
        addPoints(score,1,6);
        addPoints(score,2,5);
        assertEquals(6,score.getFirstPlayerPoints());
        assertEquals(5,score.getSecondPlayerPoints());
        addPoints(score,1,1);// сет закончен победой 7 5 первым игроком
        assertEquals(7,score.getSets()[0].getFirst());
        assertEquals(6,score.getSets()[0].getSecond()); // проверка первого счета первого сета
        assertEquals(0,score.getFirstPlayerPoints());
        assertEquals(0,score.getSecondPlayerPoints());
        assertEquals(1, score.getCurrentSet());
    }

    @Test
    public void MatchEndingTest(){}

    private void addPoints(Score score, int player, int points){
        for(int i = 0; i < points; i++){
            scoreCalculationService.addPointToPlayer(score, player);
        }
    }
}
