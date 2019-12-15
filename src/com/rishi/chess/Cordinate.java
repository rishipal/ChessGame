package com.rishi.chess;


public class Cordinate {
    public final int row;
    public final int col;

    Cordinate(int i, int j) {
        row = i;
        col = j;
    }

    public int getTileIDFromCordinate( int boardSize) {
        return this.row * boardSize + this.col;
    }

    public boolean isWithinBounds( int boardSize) {
        if (this.row >= 0 && this.col >= 0 && this.row < boardSize && this.col < boardSize) {
            return true;
        }
        return false;
    }
}