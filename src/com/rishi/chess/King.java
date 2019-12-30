package com.rishi.chess;

public class King extends Piece {

    public King(Cordinate cord, PieceColor c, ChessBoard cb) {
        super(c);
        this.board = cb;
        this.cordinate = cord;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BK.gif" : "WK.gif");
    }
}
