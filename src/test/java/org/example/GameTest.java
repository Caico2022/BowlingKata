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

    @Test
    void canScoreStrikeThenSpareInLastFrameOnly() {
        for (int i = 0; i < 9; i++) {
            game.throwBall(1);
        }
        game.throwBall(10); // Frame10
        game.throwBall(7); // Bonuswurf 1
        game.throwBall(3); // Bonuswurf 2
        // Frame 1-9: 9 · 1 = 9
        // Frame 10: 10 + Bonuswurf(7) + Bonuswurf(3) = 20
        assertEquals(29, game.score());
    }

    @Test
    void canScoreStrikesAndSparesAndNormalFrames() {
        // Frame1 Strike: 10 + 10 = 20
        game.throwBall(10);
        // Frame2 Spare: 10 + 5 = 15
        game.throwBall(5);
        game.throwBall(5);
        // Frame3-5 Normal: 7 · 3 = 21
        for (int i = 0; i < 3; i++) {
            game.throwBall(5);
            game.throwBall(2);
        }
        // Frame6 Spare: 2 + 8 + 10 = 20
        game.throwBall(2);
        game.throwBall(8);
        // Frame7 Strike: 10 + 1 + 7 = 18
        game.throwBall(10);
        // Frame8-9 Normal: 8 · 2 = 16
        for (int i = 0; i < 2; i++) {
            game.throwBall(1);
            game.throwBall(7);
        }
        // Frame10 Spare: 10 + 10 = 20
        game.throwBall(7);
        game.throwBall(3);
        game.throwBall(10); // Bonuswurf

        // Gesamt: 20 + 15 + 21 + 20 + 18 + 16 + 20 = 130
        assertEquals(130, game.score());
    }

    // ************** Unvollständige Spiele **************
    // Werden als 0-Punkte-Frames behandelt, da rolls mit Nullen gefüllt ist
    @Test
    void canScoreWithNormalFramesThenNothing() {
        game.throwBall(5);
        game.throwBall(4);
        game.throwBall(6);
        assertEquals(15, game.score());
    }
    @Test
    void canScoreWithStrikeThenNothing() {
        game.throwBall(10);
        assertEquals(10, game.score());
    }
    @Test
    void canScoreWithSpareThenNothing() {
        game.throwBall(5);
        game.throwBall(5);
        assertEquals(10, game.score());
    }

    // ************** Falsche Eingaben **************
    // Negative Zahlen werden einfach subtrahiert statt addiert
    @Test
    void canScoreWithNegativePins() {
        game.throwBall(-1);
        assertEquals(-1, game.score());
    }

    @Test
    void canScoreWithNegativePinsAndSpare() {
        game.throwBall(-1);
        game.throwBall(11);
        for (int i = 0; i < 18; i++) {
            game.throwBall(1);
        }
        // Frame1: -1 + 11 + 1 = 11
        // Frame2-10: 2 * 9 = 18
        assertEquals(29, game.score());
    }

    // ************** Zu viele Würfe **************
    @Test
    void canScore21ThrowsInAllNormalFrames() {
        for (int i = 0; i < 21; i++) {
            game.throwBall(2);
        }
        /* rolls wird zwar bis 21 Würfe gefüllt, jedoch werden nur die
        ersten 10 Frames (20 Würfe) berechnet, weil kein Spare vorliegt. */
        assertEquals(40, game.score());
    }

    @Test
    void canScore22ThrowsInAllNormalFrames() {
        for (int i = 0; i < 21; i++) {
            game.throwBall(2);
        }
        ArrayIndexOutOfBoundsException exception =
                assertThrows(ArrayIndexOutOfBoundsException.class, () -> game.throwBall(2));
        assertTrue(exception.getMessage().contains("Index 21 out of bounds"));
    }



}