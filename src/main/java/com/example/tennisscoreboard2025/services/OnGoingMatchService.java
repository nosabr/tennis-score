package com.example.tennisscoreboard2025.services;

import com.example.tennisscoreboard2025.models.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class  OnGoingMatchService {
    public static final Map <String, Match> matches = new HashMap<>();

    public static UUID createMatch(String p1, String p2) {
        Match match = new Match();
        UUID uuid = UUID.randomUUID();
    }

}
