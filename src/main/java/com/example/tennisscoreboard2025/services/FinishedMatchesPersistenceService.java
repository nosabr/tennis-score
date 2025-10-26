package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.dao.MatchDAO;
import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.models.Player;
import com.example.tennisscoreboard2025.models.Score;
import com.example.tennisscoreboard2025.models.scoreUtil.Pair;

import java.util.Optional;
import java.util.UUID;

public class FinishedMatchesPersistenceService {
    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();
    MatchDAO matchDAO = new MatchDAO();
    public void endMatch(String uuid) {
        Match match;
        Optional<Match> matchOpt = ongoingMatchService.getMatch(UUID.fromString(uuid));
        if (matchOpt.isPresent()) {
            match = matchOpt.get();
        } else {
            throw new RuntimeException("No match found with that uuid");
        }
        match.setWinner(findWinner(match));
        match.setMatchFinished(true);
        matchDAO.save(match);
        ongoingMatchService.deleteMatch(UUID.fromString(uuid));
    }

    private Player findWinner(Match match) {
        Pair[] sets = match.getScore().getSets();
        int firstPlayerWinSets = 0;
        int secondPlayerWinSets = 0;
        for (int i = 0; i < 3; i++){
            if(sets[i].getFirst() > sets[i].getSecond()){
                firstPlayerWinSets++;
            } else secondPlayerWinSets++;
        }
        if (firstPlayerWinSets > secondPlayerWinSets){
            return match.getPlayer1();
        } else return match.getPlayer2();
    }
}
