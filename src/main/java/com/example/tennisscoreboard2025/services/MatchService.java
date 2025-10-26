package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.dao.MatchDAO;
import com.example.tennisscoreboard2025.models.Match;

import java.util.Optional;
import java.util.UUID;

public class MatchService {
    ScoreCalculationService scoreCalculationService = new ScoreCalculationService();
    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();
    FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    public void handlePost(String uuid, int scoredPlayerNumber) {
        UUID matchUUID = UUID.fromString(uuid);
        Match match;
        Optional<Match> matchOpt = ongoingMatchService.getMatch(matchUUID);
        if (matchOpt.isPresent()) {
            match = matchOpt.get();
        } else {
            throw new RuntimeException("no match in uuid: " + uuid);
        }
        if (!match.isMatchFinished()){
            scoreCalculationService.addPointToPlayer(match.getScore(),scoredPlayerNumber);
        }
        if(match.getScore().getCurrentSet() == 3 && !match.isMatchFinished()){
            finishedMatchesPersistenceService.endMatch(uuid);
        }

    }

}
