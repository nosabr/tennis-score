package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.models.Match;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchService {
    private static OngoingMatchService instance;
    private static final Map <UUID, Match> matches = new ConcurrentHashMap<>();

    private OngoingMatchService() {}

    public static OngoingMatchService getInstance() {
        if(instance == null)
            instance = new OngoingMatchService();
        return instance;
    }


    public UUID putMatch( Match match){
        UUID uuid = UUID.randomUUID();
        matches.put(uuid, match);
        return uuid;
    }
    public Optional<Match> getMatch(UUID uuid){
        return  Optional.ofNullable(matches.get(uuid));
    }
    public void deleteMatch(UUID uuid){
        matches.remove(uuid);
    }
}
