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

    @Test
    void canScoreOnlyOnes() {
        for (int i = 0; i < 20; i++) {
            game.throwBall(1);
        }
        assertEquals(20, game.score());
    }

    @Test
    void canScoreSpareThen3ThenOnlyZeros() {
        game.throwBall(5);
        game.throwBall(5);
        game.throwBall(3);
        for (int i = 0; i < 17; i++) {
            game.throwBall(0);
        }
        // Frame1: 10 + 3 = 13
        // Frame2: 3 + 0 = 3
        assertEquals(16, game.score());
    }

    @Test
    void canScoreStrikeThenTwo3ThenOnlyZeros() {
        game.throwBall(10);
        game.throwBall(3);
        game.throwBall(3);
        for (int i = 0; i < 16; i++) {
            game.throwBall(0);
        }
        // Frame1: 10 + 3 + 3 = 16
        // Frame2: 3 + 3 = 6
        assertEquals(22, game.score());
    }

}