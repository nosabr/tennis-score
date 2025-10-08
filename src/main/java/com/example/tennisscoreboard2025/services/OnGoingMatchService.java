package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.models.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class  OnGoingMatchService {
    public static final Map <UUID, Match> matches = new ConcurrentHashMap<>();

    public static UUID createMatch(String p1, String p2) {
        Match match = new Match();
        UUID uuid = UUID.randomUUID();
        return uuid;
    }

}
