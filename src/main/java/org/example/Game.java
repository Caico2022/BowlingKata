package org.example;

import java.util.Arrays;

public class Game {

    private int roll = 0;
    private final int[] rolls = new int[21];


    public void throwBall(int pinsDown) {
        rolls[roll++] = pinsDown;
    }

    public int score() {
        System.out.println("rolls: " + Arrays.toString(rolls));
        int score = 0;
        int cursor = 0;
        for (int frames = 0; frames < 10; frames++) {
            if (rolls[cursor] + rolls[cursor + 1] == 10) {
                score += 10 + rolls[cursor + 2];
                cursor += 2;
            }
            else if (rolls[cursor] == 10) {
                score += 10 + rolls[cursor + 1] + rolls[cursor + 2];
                // Bei einem Strike wird der zweite Wurf desselben Frames ausgelassen
                cursor += 1;
            }
            else {
                score += rolls[cursor] + rolls[cursor + 1];
                cursor += 2;
            }
        }

        return score;
    }

}
