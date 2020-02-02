package com.rishi.chess;

import java.util.List;

class Player {
    private String name;
    Piece.PieceColor pieceColor;
    List<Move> moveList;

    Player() {}

    private void setInfo(String name, Piece.PieceColor pieceColor) {
        this.name = name;
        this.pieceColor = pieceColor;
    }
}