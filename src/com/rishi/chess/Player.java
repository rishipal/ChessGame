package com.rishi.chess;

import java.util.List;

abstract class Player {
    private String name;
    protected ChessBoard chessBoard;
    Piece.PieceColor pieceColor;
    List<Move> moveList;

    Player() {}
    public abstract void makeMove(MoveManager moveManager, Cell source, Cell destination);
}