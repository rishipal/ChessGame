package com.rishi.chess;

import java.util.List;

class Player {
    private String name;
    Piece.PieceColor pieceColor;
    List<Move> moveList;

    Player() {}

    Player(Piece.PieceColor c) {
        this.pieceColor = c;
    }

    private void setInfo(String name, Piece.PieceColor pieceColor) {
        this.name = name;
        this.pieceColor = pieceColor;
    }
}