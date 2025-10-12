package com.example.tennisscoreboard2025.daotests;

import com.example.tennisscoreboard2025.dao.PlayerDAO;
import com.example.tennisscoreboard2025.models.Player;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerDaoTest {
    PlayerDAO playerDAO = new PlayerDAO();

    @Test
    public void testSaveAndFind() {
        Player player = new Player();
        player.setName("test");
        playerDAO.save(player);
        Optional<Player> foundPlayer = playerDAO.findByName("test");
        assertTrue(foundPlayer.isPresent());
        assertEquals("test", foundPlayer.get().getName());
    }
}
