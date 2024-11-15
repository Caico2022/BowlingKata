package org.example;

import java.util.Arrays;

public class Game {

    private int roll = 0;
    // Automatisch mit Nullen gefüllt
    private final int[] rolls = new int[21];  // Mit Strike höchstens 21 Würfe

    public void rollBall(int pinsDown) {
        rolls[roll++] = pinsDown;
    }

    public int score() {
        int score = 0;
        int cursor = 0;
        for (int frames = 0; frames < 10; frames++) {
            if (rolls[cursor] + rolls[cursor + 1] == 10) {  // Bei Spare
                score += rolls[cursor] + rolls[cursor + 1] + rolls[cursor + 2];
            }
            else { // Kein Spare
                score += rolls[cursor] + rolls[cursor + 1];
            }
            cursor += 2;  // Da zwei Würfe pro Frame (ohne Strike)
            System.out.println("score: " + score + ", cursor: " + cursor
                    + ", rolls: " + Arrays.toString(rolls));
        }
        return score;
    }
}
