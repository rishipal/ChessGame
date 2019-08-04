package com.rishi.chess;

public class Knight extends Piece {
    public Knight(int x, int y, PieceColor c) {
        this.x = x;
        this.y = y;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BN.gif" : "WN.gif");
    }

    public boolean isMoveLegal(int end_x, int end_y) {
        return true;
    }

}