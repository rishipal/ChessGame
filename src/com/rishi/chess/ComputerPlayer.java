package com.rishi.chess;
import com.rishi.chess.engine.Engine;

public class ComputerPlayer extends Player {
    public ComputerPlayer(ChessBoard board, Piece.PieceColor color) {
        this.pieceColor = color;
        this.chessBoard = board;
    }

    /** TODO: Use the engine to choose a move, and then make that move */
    public final void makeAMove(Engine engine, MoveManager moveManager, Cell ignore, Cell ignore2) {
        Move move = engine.getComputerPlayerMove(this);
        if(move != null) {
            move.execute();
            moveManager.storeMove(move);
            return;
        }
    }
}