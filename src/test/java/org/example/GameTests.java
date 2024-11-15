package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameTests {

    private Game game;

    @BeforeEach
    void setUp() {  // Jeder @Test startet automatisch mit einer neuen Game-Instanz
        this.game = new Game();
    }

    @Test
    void canScoreGutterGame() {  // Kein einziger Punkt bei jedem Wurf (20)
        for (int i = 0; i < 20; i++) {
            game.rollBall(0);
        }
        assertEquals(0, game.score());
    }

    @Test
    void canScoreGameOfOnes() {
        for (int i = 0; i < 20; i++) {
            game.rollBall(1);
        }
        assertEquals(20, game.score());
    }

    @Test
    void canScoreSpareFollowedBy3ThenGutters() {
        game.rollBall(5);
        game.rollBall(5);
        game.rollBall(3);
        for (int i = 0; i < 17; i++) {
            game.rollBall(0);
        }
        assertEquals(16, game.score());
    }
}
