package com.rishi.chess;

public class Rook extends Piece {

    public Rook () {}

    public Rook (Cordinate cord) {
        this.cordinate = cord;
    }

    public Rook(Cordinate cord, PieceColor c) {
        this.cordinate = cord;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BR.gif" : "WR.gif");
    }

    public boolean isMoveLegal(int end_x, int end_y) {
        return true;
    }

}