package com.oose2016.inemzer1.dots;

/**
 * Created by Isaac on 9/19/2016.
 */
public class State {

    public int redScore;
    public int blueScore;
    public String state;
    public String whoseTurn;

    public State() {
        this.redScore = 0;
        this.blueScore = 0;
        this.state = "WAITING_TO_START";
        this.whoseTurn = "RED";
    }

    public void startIt() {
        this.state = "IN_PROGRESS";
    }

    public void nextTurn() {
        if (this.whoseTurn.equals("RED")) {
            this.whoseTurn = "BLUE";
        } else {
            this.whoseTurn = "RED";
        }
    }

    public void scoreForRed(int scores) {
        this.redScore += scores;
        System.out.println("Red scored " + String.valueOf(scores));
        this.checkDone();
    }

    public void scoreForBlue(int scores) {
        this.blueScore += scores;
        System.out.println("Blue scored " + String.valueOf(scores));
        this.checkDone();
    }

    public void checkDone() {
        if ((this.redScore + this.blueScore) == 16) {
            this.state = "FINISHED";
        }
    }

    @Override
    public String toString() {
        return this.state + " Red:" + String.valueOf(this.redScore) + " Blue:" +
                String.valueOf(this.blueScore) + " Turn:" + this.whoseTurn;
    }
}
