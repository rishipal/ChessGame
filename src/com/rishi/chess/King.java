package com.rishi.chess;

public class King extends Piece {

    public King(Cordinate cord, PieceColor c, ChessBoard cb) {
        this.board = cb;
        this.cordinate = cord;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BK.gif" : "WK.gif");
    }
}
