package com.oose2016.inemzer1.dots;

/**
 * Created by Isaac on 9/19/2016.
 */
public class Board {

    public Line[] horizontalLines;
    public Line[] verticalLines;
    public Box[] boxes;

    public Board() {
        this.horizontalLines = new Line[20];
        this.verticalLines = new Line[20];
        int count = 0;
        for (int a = 0; a < 5; a++) {
            for (int b = 5; b < 4; b++) {
                this.verticalLines[count] = new Line(a, b, false, 'v');
                this.horizontalLines[count] = new Line(b, a, false, 'h');
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

    public boolean checkHori(int r, int c) {
        for (int a = 0;  a < 20; a++) {
            if (r == this.horizontalLines[a].row &&
                    c == this.horizontalLines[a].col) {
                if (this.horizontalLines[a].filled) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean checkVert(int r, int c) {
        for (int a = 0;  a < 20; a++) {
            if (r == this.verticalLines[a].row &&
                    c == this.verticalLines[a].col) {
                if (this.verticalLines[a].filled) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public int checkHoriScore(Move move) {
        int scores = 0;
        switch (move.row) {
            case 0:
                //check down
                if (this.checkVert(move.row, move.col) &&
                        this.checkVert(move.row, move.col + 1) &&
                        this.checkHori(move.row + 1, move.col)) {
                    this.boxes[move.col].fill(move.playerId);
                    scores++;
                }
                break;
            case 1:
            case 2:
            case 3:
                //check down and up
                if (this.checkVert(move.row, move.col) &&
                        this.checkVert(move.row, move.col + 1) &&
                        this.checkHori(move.row + 1, move.col)) {
                    this.boxes[(4 * move.row) + move.col].fill(move.playerId);
                    scores++;
                }
                if (this.checkVert(move.row - 1, move.col) &&
                        this.checkVert(move.row - 1, move.col + 1) &&
                        this.checkHori(move.row - 1, move.col)) {
                    this.boxes[(4 * (move.row - 1)) + move.col].fill(move.playerId);
                    scores++;
                }
                break;
            case 4:
                //check up
                if (this.checkVert(move.row - 1, move.col) &&
                        this.checkVert(move.row - 1, move.col + 1) &&
                        this.checkHori(move.row - 1, move.col)) {
                    this.boxes[(4 * (move.row - 1)) + move.col].fill(move.playerId);
                    scores++;
                }
                break;
            default:
                break;
        }
        return scores;
    }

    public int checkVertScore(Move move) {
        int scores = 0;
        switch (move.col) {
            case 0:
                //check right
                if (this.checkHori(move.row, move.col) &&
                        this.checkHori(move.row + 1, move.col) &&
                        this.checkVert(move.row, move.col + 1)) {
                    this.boxes[4 * move.row].fill(move.playerId);
                    scores++;
                }
                break;
            case 1:
            case 2:
            case 3:
                // check right and left
                if (this.checkHori(move.row, move.col) &&
                        this.checkHori(move.row + 1, move.col) &&
                        this.checkVert(move.row, move.col + 1)) {
                    this.boxes[(4 * move.row) + move.col].fill(move.playerId);
                    scores++;
                }
                if (this.checkHori(move.row - 1, move.col) &&
                        this.checkHori(move.row + 1, move.col - 1) &&
                        this.checkVert(move.row, move.col -1)) {
                    this.boxes[(4 * move.row) + move.col].fill(move.playerId);
                    scores++;
                }
                break;
            case 4:
                //check left
                if (this.checkHori(move.row - 1, move.col) &&
                        this.checkHori(move.row + 1, move.col - 1) &&
                        this.checkVert(move.row, move.col -1)) {
                    this.boxes[(4 * move.row) + move.col].fill(move.playerId);
                    scores++;
                }
                break;
            default:
                break;
        }
        return scores;
    }

    public boolean moveHorizontal(Move move) {
        return !this.checkHori(move.row, move.col);
    }

    public boolean moveVertical(Move move) {
        return !this.checkVert(move.row, move.col);
    }

}
