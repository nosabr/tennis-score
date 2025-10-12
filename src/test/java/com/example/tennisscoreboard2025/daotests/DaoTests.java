package com.example.tennisscoreboard2025.daotests;

import com.example.tennisscoreboard2025.dao.MatchDAO;
import com.example.tennisscoreboard2025.dao.PlayerDAO;
import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.models.Player;
import com.example.tennisscoreboard2025.services.MatchGenerationService;
import com.example.tennisscoreboard2025.services.OngoingMatchService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoTests {
    PlayerDAO playerDAO = new PlayerDAO();
    MatchGenerationService matchGenerationService = new MatchGenerationService();
    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();
    MatchDAO matchDAO = new MatchDAO();

    @Test
    public void testSaveAndFindPlayer() {
        Player player = new Player();
        player.setName("test");
        playerDAO.save(player);
        Optional<Player> foundPlayer = playerDAO.findByName("test");
        assertTrue(foundPlayer.isPresent());
        assertEquals("test", foundPlayer.get().getName());
    }

    @Test
    public void testCreateMatch(){
        Match match = matchGenerationService.generateNewMatch("Pupa", "Lupa");
        UUID uuid = ongoingMatchService.putMatch(match);
        assertTrue(ongoingMatchService.getMatch(uuid).isPresent());
        assertEquals(match, ongoingMatchService.getMatch(uuid).get());
    }

    @Test
    public void testForPlayerInDataBaseAfterGenerateMatchMethod(){
        Match match = matchGenerationService.generateNewMatch("Sabr", "Ali");
        assertTrue(playerDAO.findByName("Sabr").isPresent());
        assertTrue(playerDAO.findByName("Ali").isPresent());
    }

    @Test
    public void testSaveMatch(){
        Match match = matchGenerationService.generateNewMatch("Pupa", "Lupa");
        UUID uuid = ongoingMatchService.putMatch(match);
        match.setWinner(match.getPlayer1());
        matchDAO.save(match);
        ongoingMatchService.deleteMatch(uuid);
        assertTrue(ongoingMatchService.getMatch(uuid).isEmpty());
        assertTrue(matchDAO.findById(match.getId()).isPresent());
        assertEquals(match, matchDAO.findById(match.getId()).get());
    }
}
