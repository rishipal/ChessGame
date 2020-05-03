package com.rishi.chess;

import com.rishi.chess.engine.Engine;

public class HumanPlayer extends Player {
    public HumanPlayer( ChessBoard board, Piece.PieceColor color) {
        this.pieceColor = color;
        this.chessBoard = board;
    }

    // TODO(rishipal): Make the HumanPlayer not receive an engine object when makign a move. So make this function
    // unique to this class instead of overloading
    /** Simply make the move from source to destination */
    public final void makeAMove(Engine engine, MoveManager moveManager, Cell source, Cell destination) {
        Move m = new Move(source.piece, destination);
        m.execute();
        moveManager.storeMove(m);
    }
}