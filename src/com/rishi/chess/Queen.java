package com.rishi.chess;

public class Queen extends Piece {

    public Queen(Cordinate cord, PieceColor c, ChessBoard cb) {
        super(c);
        this.board = cb;
        this.cordinate = cord;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BQ.gif" : "WQ.gif");
    }
}
