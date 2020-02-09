package com.rishi.chess;

import java.util.List;

abstract class Player {
    private String name;
    Piece.PieceColor pieceColor;
    List<Move> moveList;

    Player() {}

    Player(Piece.PieceColor c) {
        this.pieceColor = c;
    }
    public abstract void makeMove(MoveManager moveManager, Cell source, Cell destination);
}