package com.oose2016.inemzer1.dots;

/**
 * Created by Isaac on 9/19/2016.
 */
public class Line {

    public int row;
    public int col;
    public boolean filled;
    public transient char dir;

    public Line(int r, int c, boolean f, char d) {
        this.row = r;
        this.col = c;
        this.filled = f;
        this.dir = d;
    }

    public void fill() {
        this.filled = true;
    }

}
