package com.rishi.chess;

public class Piece {
    protected int x;
    protected int y;

    public Piece() {}

    public Piece (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isMoveLegal(int end_x, int end_y) {
        System.out.println("This should not be called");
        return true;
    }
}

