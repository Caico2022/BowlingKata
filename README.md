# Bowling Kata
This is a solution for the Bowling Kata (https://codingdojo.org/kata/Bowling/), a problem where the goal is to calculate the score of a bowling game. The game consists of 10 frames, and in each frame, the player has up to two attempts to knock down all the pins.
This program calculates the score according to the rules of American Ten-Pin Bowling.

## How It Works
- A game consists of 10 frames.
- In each frame, the player has up to two attempts.
- A strike is when all 10 pins are knocked down with the first roll of a frame. The score for the frame is 10 plus the points from the next two rolls.
- A spare is when the player knocks down all 10 pins in two rolls. The score for the frame is 10 plus the next roll.
- The 10th frame allows extra rolls if a strike (one extra roll) or spare (two extra rolls) is scored.

The program implements these rules and calculates the total score, including bonus rolls for strikes and spares in the 10th frame.

## Technologies Used
- Java 21 (or higher)
- JUnit 5 for unit testing
- Maven for project management and dependencies

## Features
- Calculation of the total score for a bowling game.
- Support for strikes, spares, and regular frames.
- Input for rolls and corresponding score calculation.
- JUnit 5 tests with high test coverage.
