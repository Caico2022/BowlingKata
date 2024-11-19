package org.example;

import java.util.Arrays;

public class Game {

    private int roll = 0;
    // Mit Spare sind 21 Würfe möglich
    private final int[] rolls = new int[21];


    public void throwBall(int pinsDown) {
        // Zuerst wird das aktuelle Element gefüllt und dann wird die Position inkrementiert
        rolls[roll++] = pinsDown;
    }

    public int score() {
        System.out.println("rolls: " + Arrays.toString(rolls));
        int score = 0;
        int cursor = 0;
        // Ein Frame hat 2 Würfe
        for (int frames = 0; frames < 10; frames++) {
            if (rolls[cursor] + rolls[cursor + 1] == 10) { // Spare
                score += 10 + rolls[cursor + 2];
                cursor += 2;
            }
            else {
                score += rolls[cursor] + rolls[cursor + 1];
                cursor += 2;
            }
        }

        return score;
    }

}
