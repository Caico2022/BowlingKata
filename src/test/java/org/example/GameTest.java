package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        this.game = new Game();
    }

    @Test
    void canScoreOnlyZeros() {
        for (int i = 0; i < 20; i++) {
            game.throwBall(0);
        }
        assertEquals(0, game.score());
    }

}