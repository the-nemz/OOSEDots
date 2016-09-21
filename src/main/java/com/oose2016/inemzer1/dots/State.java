package com.oose2016.inemzer1.dots;

/**
 * Class to hold data about a game's state as well as handle
 * things like updating score, current turn, etc.
 * Created by Isaac on 9/19/2016.
 */
public class State {

    public int redScore;
    public int blueScore;
    public String state;
    public String whoseTurn;

    /**
     * Simple constructor for the beginning of the game.
     */
    public State() {
        this.redScore = 0;
        this.blueScore = 0;
        this.state = "WAITING_TO_START";
        this.whoseTurn = "RED";
    }

    /**
     * Changes the game state to IN_PROGRESS.
     */
    public void startIt() {
        this.state = "IN_PROGRESS";
    }

    /**
     * Switches the current turn.
     */
    public void nextTurn() {
        if (this.whoseTurn.equals("RED")) {
            this.whoseTurn = "BLUE";
        } else {
            this.whoseTurn = "RED";
        }
    }

    /**
     * For when red scores one or two points.
     * @param scores one or two points
     */
    public void scoreForRed(int scores) {
        this.redScore += scores;
        System.out.println("Red scored " + String.valueOf(scores));
        this.checkDone();
    }

    /**
     * For when blue scores one or two points.
     * @param scores one or two points
     */
    public void scoreForBlue(int scores) {
        this.blueScore += scores;
        System.out.println("Blue scored " + String.valueOf(scores));
        this.checkDone();
    }

    /**
     * Check to see if the score adds to 16, meaning the game is done.
     */
    public void checkDone() {
        if ((this.redScore + this.blueScore) == 16) {
            this.state = "FINISHED";
        }
    }

    /**
     * A toString for testing.
     * @return the string representation
     */
    @Override
    public String toString() {
        return this.state + " Red:" + String.valueOf(this.redScore) + " Blue:" +
                String.valueOf(this.blueScore) + " Turn:" + this.whoseTurn;
    }
}
