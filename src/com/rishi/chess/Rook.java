package com.rishi.chess;

public class Rook extends Piece {

    public Rook () {}

    public Rook (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isMoveLegal(int end_x, int end_y) {
        return true;
    }

}