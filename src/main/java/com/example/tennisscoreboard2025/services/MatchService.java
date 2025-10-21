package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.dao.MatchDAO;
import com.example.tennisscoreboard2025.models.Match;

import java.util.Optional;
import java.util.UUID;

public class MatchService {
    ScoreService scoreService = new ScoreService();
    Match match;
    MatchDAO matchDAO = new MatchDAO();
    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();
    public void handlePost(String uuid, int scoredPlayerNumber){
        UUID matchUUID = UUID.fromString(uuid);
        Optional<Match> matchOpt =  ongoingMatchService.getMatch(matchUUID);
        if (matchOpt.isPresent()){
            match = matchOpt.get();
        } else {
            throw new RuntimeException("Match not found");
        }
        match = scoreService.addPointToPlayer(match,scoredPlayerNumber);
    }

}
