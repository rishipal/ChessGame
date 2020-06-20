package com.rishi.chess;

public class HumanPlayer extends Player {
    public HumanPlayer( ChessBoard board, Piece.PieceColor color) {
        this.pieceColor = color;
        this.chessBoard = board;
    }

    /** Simply make the move from source to destination */
    public final void makeAMove(MoveManager moveManager, Cell source, Cell destination) {
        Move m = new Move(source.piece, destination);
        m.execute();
        this.moveList.add(m);
        moveManager.storeMove(m);
    }
}