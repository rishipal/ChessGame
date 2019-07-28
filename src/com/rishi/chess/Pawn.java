package com.rishi.chess;

class Pawn extends Piece {
    Pawn(int x, int y, PieceColor pieceColor) {
        this.x = x;
        this.y = y;
        this.pieceColor = pieceColor;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BP.gif" : "WP.gif");
    }
}