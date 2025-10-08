package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.dao.PlayerDAO;
import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.models.Player;

import java.util.Optional;

public class MatchGenerationService {
    PlayerDAO playerDAO = new PlayerDAO();

    public Match generateNewMatch(String firstPlayerName, String secondPlayerName){
        Player firstPlayer = getOrSavePlayer(firstPlayerName);
        Player secondPlayer = getOrSavePlayer(secondPlayerName);
        Match match = new Match();
        match.setPlayer1(firstPlayer);
        match.setPlayer2(secondPlayer);
        return match;
    }



    private Player getOrSavePlayer(String firstPlayerName){
        return playerDAO.findByName(firstPlayerName)
                .orElseGet(() -> {
                    Player newPlayer = new Player();
                    newPlayer.setName(firstPlayerName);
                    playerDAO.save(newPlayer);
                    return newPlayer;
                });
    }
}
