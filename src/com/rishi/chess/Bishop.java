package com.rishi.chess;

public class Bishop extends Piece {

    public Bishop(Cordinate cord, PieceColor c, ChessBoard cb) {
        this.board = cb;
        this.cordinate = cord;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BB.gif" : "WB.gif");
    }
}
