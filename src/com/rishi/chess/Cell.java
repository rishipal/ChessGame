package com.rishi.chess;

public class Cell {
    private final ChessBoard board;
    //TODO: Use cordinate here instead of row and col
    public int row, col;
    public boolean occupied = false;
    public Piece piece = null;
    public Cell( int x, int y, ChessBoard cb) {
        this.row = x;
        this.col = y;
        this.board = cb;
    }

    public void setPiece(Piece p) {
        this.piece = p;
        this.occupied = true;
        this.piece.cell = this;
    }

    public Cordinate getCordinate() {
        Cordinate c = new Cordinate(this.row, this.col);
        return c;
    }

    public int getTileIDFromCell() {
        return this.row * board.SIZE_BOARD + this.col;
    }
}