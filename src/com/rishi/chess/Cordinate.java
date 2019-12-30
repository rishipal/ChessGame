package com.rishi.chess;


public class Cordinate {
    public final int row;
    public final int col;

    Cordinate(int i, int j) {
        row = i;
        col = j;
    }

    public boolean isSameColumn(Cordinate another) {
        if(this.col == another.col) {
            return true;
        }
        return false;
    }

    public boolean isSameRow(Cordinate another) {
        if(this.row == another.row) {
            return true;
        }
        return false;
    }

    public int getTileIDFromCordinate( int boardSize) {
        return this.row * boardSize + this.col;
    }

    public Cordinate getCordinateAbove() {
        return new Cordinate(this.row-1, this.col);
    }

    public Cordinate getCordinateBelow() {
        return new Cordinate(this.row+1, this.col);
    }

    public boolean isWithinBounds( int boardSize) {
        if (this.row >= 0 && this.col >= 0 && this.row < boardSize && this.col < boardSize) {
            return true;
        }
        return false;
    }
}