package com.rishi.chess;


class Cordinate {
    public final int row;
    public final int col;

    Cordinate(int i, int j) {
        row = i;
        col = j;
    }

    public boolean isWithinBounds( int boardSize) {
        if (this.row < boardSize && this.col < boardSize) {
            return true;
        }
        return false;
    }
}