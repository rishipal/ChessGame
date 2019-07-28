package com.rishi.chess;
import java.awt.*;

class Cell {
    private int row, col;
    public boolean occupied = false;
    public Piece piece = null;
    public Cell( int x, int y) {
        this.row = x;
        this.col = y;
    }

    public void setPiece(Piece p) {
        this.piece = p;
        this.occupied = true;
    }
}