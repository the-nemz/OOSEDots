package com.oose2016.inemzer1.dots;

/**
 * Created by Isaac on 9/19/2016.
 */
public class Line {

    public int row;
    public int col;
    public boolean filled;
    public transient char dir;

    /**
     * Simple line constructor.
     * @param r the row
     * @param c the col
     * @param f if filled
     * @param d the direction
     */
    public Line(int r, int c, boolean f, char d) {
        this.row = r;
        this.col = c;
        this.filled = f;
        this.dir = d;
    }

    /**
     * Just fills the line.
     */
    public void fill() {
        this.filled = true;
    }

    /**
     * A toString for testing.
     * @return the string representation
     */
    @Override
    public String toString() {
        return "R" + String.valueOf(this.row) + " C" + String.valueOf(this.col) +
               " " + this.dir + " " + String.valueOf(this.filled);
    }

}
