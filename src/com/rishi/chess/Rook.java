package com.rishi.chess;

public class Rook extends Piece {

    public Rook () {}

    public Rook (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rook(int x, int y, PieceColor c) {
        this.x = x;
        this.y = y;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BR.gif" : "WR.gif");
    }

    public boolean isMoveLegal(int end_x, int end_y) {
        return true;
    }

}