package org.example;

import java.util.Arrays;


public class Game {

    private static final boolean DEBUG = true;
    private static final int MAX_FRAMES = 10;

    private int roll = 0;
    // Bei einem Spare im letzten Frame ein Bonuswurf möglich
    // Bei Strikes wird der zweite Wurf übersprungen und nicht im Array angezeigt
    private final int[] rolls = new int[21];


    public void throwBall(int pinsDown) {
        rolls[roll++] = pinsDown;
    }

    public int score() {
        if (DEBUG)  System.out.println("rolls: " + Arrays.toString(rolls));
        int score = 0;
        int cursor = 0;
        for (int frames = 0; frames < MAX_FRAMES; frames++) {
            if (isSpare(cursor)) {
                score += calculateSpareBonus(cursor);
                cursor += 2;
            }
            else if (isStrike(cursor)) {
                score += calculateStrikeBonus(cursor);
                cursor += 1;
            }
            else {
                score += calculateNormalFrame(cursor);
                cursor += 2;
            }
        }
        return score;
    }


    private int calculateNormalFrame(int cursor) {
        return rolls[cursor] + rolls[cursor + 1];
    }

    private int calculateStrikeBonus(int cursor) {
        return 10 + rolls[cursor + 1] + rolls[cursor + 2];
    }

    private int calculateSpareBonus(int cursor) {
        return 10 + rolls[cursor + 2];
    }

    private boolean isStrike(int cursor) {
        return rolls[cursor] == 10;
    }

    private boolean isSpare(int cursor) {
        return rolls[cursor] + rolls[cursor + 1] == 10;
    }
}
