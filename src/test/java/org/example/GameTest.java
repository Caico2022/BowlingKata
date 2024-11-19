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

    @Test
    void canScoreOnlyStrikes() {
        // Bei Strike im letzten Frame zwei Bonuswürfe
        for (int i = 0; i < 12; i++) {
            game.throwBall(10);
        }
        // Frame1: 10 + 10 + 10 = 30
        // ...
        // Frame 10: 10 + Bonuswurf1(10) + Bonuswurf2(10) = 30
        assertEquals(300, game.score());
    }

    @Test
    void canScoreOnlySpares() {
        for (int i = 0; i < 21; i++) {
            game.throwBall(5);
        }
        // Frame1: 5 + 5 + 5 = 15
        // ...
        // Frame 10: 5 + 5 + Bonuswurf(5) = 15
        assertEquals(150, game.score());
    }

    @Test
    void canScoreOnly9With0() {
        for (int i = 0; i < 10; i++) {
            game.throwBall(9);
            game.throwBall(0);
        }
        assertEquals(90, game.score());
    }

    @Test
    void canScoreOnly0With9() {
        for (int i = 0; i < 10; i++) {
            game.throwBall(0);
            game.throwBall(9);
        }
        assertEquals(90, game.score());
    }

}