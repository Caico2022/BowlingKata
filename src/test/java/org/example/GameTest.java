package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        this.game = new Game();
    }

    @Nested
    class OnlyOneUnitCompleteGames {
        @Test
        void canScoreOnlyZeros() {
            rollMany(20, 0);
            assertEquals(0, game.score());
        }
        @Test
        void canScoreOnlyOnes() {
            rollMany(20, 1);
            assertEquals(20, game.score());
        }
        @Test
        void canScoreOnlyStrikes() {
            // Bei Strike im letzten Frame zwei Bonuswürfe
            rollMany(12, 10);
            // Frame1: 10 + 10 + 10 = 30 ...
            // Frame10: 10 + Bonuswurf1(10) + Bonuswurf2(10) = 30
            assertEquals(300, game.score());
        }
        @Test
        void canScoreOnlySpares() {
            rollMany(21, 5);
            // Frame1: 5 + 5 + 5 = 15 ...
            // Frame10: 5 + 5 + Bonuswurf(5) = 15
            assertEquals(150, game.score());
        }
    }

    @Nested
    class MixedCompleteGames {
        @Test
        void canScoreSpareThen3ThenOnlyZeros() {
            rollSpare(5, 5);
            game.throwBall(3);
            rollMany(17, 0);
            // Frame1: 10 + 3 = 13
            // Frame2: 3 + 0 = 3
            assertEquals(16, game.score());
        }
        @Test
        void canScoreStrikeThenTwo3ThenOnlyZeros() {
            rollStrike();
            rollMany(2,3);
            rollMany(16, 0);
            // Frame1: 10 + 3 + 3 = 16
            // Frame2: 3 + 3 = 6
            assertEquals(22, game.score());
        }
        @Test
        void canScoreOnly9With0() {
            rollManyWithDifferentPinsDown(10, 9, 0);
            assertEquals(90, game.score());
        }
        @Test
        void canScoreOnly0With9() {
            rollManyWithDifferentPinsDown(10, 0, 9);
            assertEquals(90, game.score());
        }
        @Test
        void canScoreStrikeThenSpareInLastFrameOnly() {
            rollMany(9, 1);
            rollStrike();
            // Bonuswurf 1 und 2
            rollManyWithDifferentPinsDown(1,7,3);
            // Frame 1-9: 9 · 1 = 9
            // Frame 10: 10 + Bonuswurf(7) + Bonuswurf(3) = 20
            assertEquals(29, game.score());
        }
        @Test
        void canScoreStrikesAndSparesAndNormalFrames() {
            // Frame1 Strike: 10 + 10 = 20
            rollStrike();
            // Frame2 Spare: 10 + 5 = 15
            rollSpare(5,5);
            // Frame3-5 Normal: 7 · 3 = 21
            rollManyWithDifferentPinsDown(3, 5, 2);
            // Frame6 Spare: 2 + 8 + 10 = 20
            rollSpare(2, 8);
            // Frame7 Strike: 10 + 1 + 7 = 18
            rollStrike();
            // Frame8-9 Normal: 8 · 2 = 16
            rollManyWithDifferentPinsDown(2, 1, 7);
            // Frame10 Spare: 10 + 10 = 20
            rollSpare(7, 3);
            game.throwBall(10); // Bonuswurf

            // Gesamt: 20 + 15 + 21 + 20 + 18 + 16 + 20 = 130
            assertEquals(130, game.score());
        }
    }

    @Nested
    class MixedIncompleteGames {
        // Werden als 0-Punkte-Frames behandelt, da rolls mit Nullen gefüllt ist
        @Test
        void canScoreWithNormalFramesThenNothing() {
            rollManyWithDifferentPinsDown(1,5,4);
            rollMany(1,6);
            assertEquals(15, game.score());
        }
        @Test
        void canScoreWithStrikeThenNothing() {
            rollStrike();
            assertEquals(10, game.score());
        }
        @Test
        void canScoreWithSpareThenNothing() {
            rollSpare(5, 5);
            assertEquals(10, game.score());
        }
    }

    @Nested
    class MixedGamesWithIncorrectInput {
        // Negative Zahlen werden einfach subtrahiert statt addiert
        @Test
        void canScoreWithNegativePins() {
            game.throwBall(-1);
            assertEquals(-1, game.score());
        }
        @Test
        void canScoreWithNegativePinsAndSpare() {
            rollSpare(-1, 11);
            rollMany(18, 1);
            // Frame1: -1 + 11 + 1 = 11
            // Frame2-10: 2 * 9 = 18
            assertEquals(29, game.score());
        }
    }

    @Nested
    class GamesWithTooManyThrows {
        @Test
        void canScore21ThrowsInAllNormalFrames() {
            rollMany(21, 2);
            /* rolls wird zwar bis 21 Würfe gefüllt, jedoch werden nur die
            ersten 10 Frames (20 Würfe) berechnet, weil kein Spare vorliegt. */
            assertEquals(40, game.score());
        }
        @Test
        void canScore22ThrowsInAllNormalFrames() {
            // typischer out of bound Fehler
            try {
                rollMany(22, 2);
                fail("Expected ArrayIndexOutOfBoundsException");
            } catch (ArrayIndexOutOfBoundsException e) {
                assertTrue(e.getMessage().contains("Index 21 out of bounds for length 21"));
            }
        }
    }


    private void rollMany(int rolls, int pinsDown) {
        for (int i = 0; i < rolls; i++) {
            game.throwBall(pinsDown);
        }
    }

    private void rollSpare(int pinsDown1, int pinsDown2) {
        game.throwBall(pinsDown1);
        game.throwBall(pinsDown2);
    }

    private void rollStrike() {
        game.throwBall(10);
    }

    private void rollManyWithDifferentPinsDown(int rolls, int pinsDown1, int pinsDown2) {
        for (int i = 0; i < rolls; i++) {
            game.throwBall(pinsDown1);
            game.throwBall(pinsDown2);
        }
    }

}