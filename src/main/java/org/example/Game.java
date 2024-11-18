package org.example;

public class Game {

    private int score = 0;

    public void throwBall(int pinsDown) {
        score += pinsDown;
    }

    public int score() {

        return score;
    }

}