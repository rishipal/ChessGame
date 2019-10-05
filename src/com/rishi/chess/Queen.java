package com.rishi.chess;

public class Queen extends Piece {

    public Queen(Cordinate cord, PieceColor c, ChessBoard cb) {
        this.board = cb;
        this.cordinate = cord;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BQ.gif" : "WQ.gif");
    }
}
