package com.rishi.chess;

import java.util.ArrayList;

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

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        return null;
    }

    public boolean isMoveLegal(int end_x, int end_y) {
        return true;
    }

}