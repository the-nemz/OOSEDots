package com.oose2016.inemzer1.dots;

/**
 * Created by Isaac on 9/19/2016.
 */
public class Box {

    public int row;
    public int col;
    public String owner;

    public Box(int r, int c) {
        this.row = r;
        this.col = c;
        this.owner = "NONE";
    }

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

    @Override
    public String toString() {
        return "R" + String.valueOf(this.row) + " C" + String.valueOf(this.col) +
                " " + this.owner;
    }

}
