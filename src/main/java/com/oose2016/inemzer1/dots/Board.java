package com.oose2016.inemzer1.dots;

/**
 * Holds the data about the board and checks if moves are valid and
 * if they filled a box.
 * Created by Isaac on 9/19/2016.
 */
public class Board {

    public Line[] horizontalLines;
    public Line[] verticalLines;
    public Box[] boxes;

    /**
     * Constructor for board that initializes all the lines and boxes
     * and puts them into their respective arrays.
     */
    public Board() {
        this.horizontalLines = new Line[20];
        this.verticalLines = new Line[20];
        int count = 0;
        for (int a = 0; a < 5; a++) {
            for (int b = 0; b < 4; b++) {
                this.verticalLines[count] = new Line(b, a, false, 'v');
                this.horizontalLines[count] = new Line(a, b, false, 'h');
                count++;
            }
        }

        this.boxes = new Box[16];
        count = 0;
        for (int c = 0; c < 4; c++) {
            for (int d = 0; d < 4; d++) {
                this.boxes[count] = new Box(c, d);
                count++;
            }
        }
    }

    /**
     * Checks if it is a horizontal valid move. Returns index in array
     * if the line is found and not already filled; otherwise returns -1
     * Returns -2 if error
     * @param r the row
     * @param c the col
     * @return explained above
     */
    public int checkHori(int r, int c) {
        for (int a = 0;  a < 20; a++) {
            if (r == this.horizontalLines[a].row &&
                    c == this.horizontalLines[a].col) {
                if (!this.horizontalLines[a].filled) {
                    return a;
                } else {
                    return -1;
                }
            }
        }
        return -2;
    }

    /**
     * Checks if it is a vertical valid move. Returns index in array
     * if the line is found and not already filled; otherwise returns -1
     * Returns -2 if error
     * @param r the row
     * @param c the col
     * @return explained above
     */
    public int checkVert(int r, int c) {
        for (int a = 0;  a < 20; a++) {
            if (r == this.verticalLines[a].row &&
                    c == this.verticalLines[a].col) {
                if (!this.verticalLines[a].filled) {
                    return a;
                } else {
                    return -1;
                }
            }
        }
        return -2;
    }

    /**
     * To check for a score on a horizontal move, there are three
     * possibilities, that the line is on the top (row 0), bottom (row 4),
     * or somewhere in the middle (row 1-3). If it is on top you need to
     * check below it, on bottom above it, or in the middle on both sides
     * of it for a score.
     * @param move the Move
     * @return the number of scores (0, 1, or 2)
     */
    public int checkHoriScore(Move move) {
        int scores = 0;
        switch (move.row) {
            case 0:
                //check down
                if ((this.checkVert(move.row, move.col) == -1) &&
                        (this.checkVert(move.row, move.col + 1) == -1) &&
                        (this.checkHori(move.row + 1, move.col) == -1)) {
                    this.boxes[move.col].fill(move.playerId);
                    scores++;
                }
                break;
            case 1:
            case 2:
            case 3:
                //check down and up
                if ((this.checkVert(move.row, move.col) == -1) &&
                        (this.checkVert(move.row, move.col + 1) == -1) &&
                        (this.checkHori(move.row + 1, move.col) == -1)) {
                    this.boxes[(4 * move.row) + move.col].fill(move.playerId);
                    scores++;
                }
                if ((this.checkVert(move.row - 1, move.col) == -1) &&
                        (this.checkVert(move.row - 1, move.col + 1) == -1) &&
                        (this.checkHori(move.row - 1, move.col) == -1)) {
                    this.boxes[(4 * (move.row - 1)) + move.col].fill(move.playerId);
                    scores++;
                }
                break;
            case 4:
                //check up
                if ((this.checkVert(move.row - 1, move.col) == -1) &&
                        (this.checkVert(move.row - 1, move.col + 1) == -1) &&
                        (this.checkHori(move.row - 1, move.col) == -1)) {
                    this.boxes[(4 * (move.row - 1)) + move.col].fill(move.playerId);
                    scores++;
                }
                break;
            default:
                break;
        }
        return scores;
    }

    /**
     * To check for a score on a vertical move, there are three
     * possibilities, that the line is on the far left(col 0), far right
     * (col 4), or somewhere in the middle (col 1-3). If it is on the far
     * left you need to check to the right of it, on the far right the
     * left of it, or in the middle on both sides of it for a score.
     * @param move the Move
     * @return the number of scores (0, 1, or 2)
     */
    public int checkVertScore(Move move) {
        int scores = 0;
        switch (move.col) {
            case 0:
                //check right
                if ((this.checkHori(move.row, move.col) == -1) &&
                        (this.checkHori(move.row + 1, move.col) == -1) &&
                        (this.checkVert(move.row, move.col + 1) == -1)) {
                    this.boxes[4 * move.row].fill(move.playerId);
                    scores++;
                }
                break;
            case 1:
            case 2:
            case 3:
                //check right and left
                if ((this.checkHori(move.row, move.col) == -1) &&
                        (this.checkHori(move.row + 1, move.col) == -1) &&
                        (this.checkVert(move.row, move.col + 1) == -1)) {
                    this.boxes[(4 * move.row) + move.col].fill(move.playerId);
                    scores++;
                }
                if ((this.checkHori(move.row, move.col - 1) == -1) &&
                        (this.checkHori(move.row + 1, move.col - 1) == -1) &&
                        (this.checkVert(move.row, move.col - 1) == -1)) {
                    this.boxes[(4 * move.row) + move.col - 1].fill(move.playerId);
                    scores++;
                }
                break;
            case 4:
                //check left
                if ((this.checkHori(move.row, move.col - 1) == -1) &&
                        (this.checkHori(move.row + 1, move.col - 1) == -1) &&
                        (this.checkVert(move.row, move.col - 1) == -1)) {
                    this.boxes[(4 * move.row) + move.col - 1].fill(move.playerId);
                    scores++;
                }
                break;
            default:
                break;
        }
        return scores;
    }

    /**
     * Makes the horizontal move after calling a function to check if
     * it is valid.
     * @param move the Move
     * @return true or false depending on success
     */
    public boolean moveHorizontal(Move move) {
        int ind = this.checkHori(move.row, move.col);
        if (ind >= 0) {
            this.horizontalLines[ind].fill();
            return true;
        }
        return false;
    }

    /**
     * Makes the vertical move after calling a function to check if it
     * is valid.
     * @param move the Move
     * @return true or false depending on success
     */
    public boolean moveVertical(Move move) {
        int ind = this.checkVert(move.row, move.col);
        if (ind >= 0) {
            this.verticalLines[ind].fill();
            return true;
        }
        return false;
    }

    /**
     * A toString used for testing.
     * @return the string representation.
     */
    @Override
    public String toString() {
        String out = "";
        for (int a = 0; a < 20; a++) {
            out = out + this.horizontalLines[a] + "\n";
        }
        for (int a = 0; a < 20; a++) {
            out = out + this.verticalLines[a] + "\n";
        }
        for (int a = 0; a < 16; a++) {
            out = out + this.boxes[a] + "\n";
        }
        return out;
    }

}
