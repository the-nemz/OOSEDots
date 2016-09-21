package com.oose2016.inemzer1.dots;

/**
 * Class to hold data about a box.
 * Created by Isaac on 9/19/2016.
 */
public class Box {

    public int row;
    public int col;
    public String owner;

    /**
     * Constructor to assign a row, col, and "NONE" for owner.
     * @param r the row
     * @param c the col
     */
    public Box(int r, int c) {
        this.row = r;
        this.col = c;
        this.owner = "NONE";
    }

    /**
     * Fills the box with color depending on the player.
     * @param playerId the playerId
     */
    public void fill(String playerId) {
        if (playerId.equals("1")) {
            this.fillRed();
        } else {
            this.fillBlue();
        }
    }

    public void fillRed() {
        this.owner = "RED";
    }

    public void fillBlue() {
        this.owner = "BLUE";
    }

    /**
     * A toString for testing.
     * @return the string representation
     */
    @Override
    public String toString() {
        return "R" + String.valueOf(this.row) + " C" + String.valueOf(this.col) +
                " " + this.owner;
    }

}
