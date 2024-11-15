package org.example;

public class Game {

    private int score = 0;

    public void rollBall(int pinsDown) {
        score += pinsDown;
    }

    public int score() {
        return score;
    }
}
