package com.rishi.chess;
import com.rishi.chess.engine.Engine;

public class ComputerPlayer extends Player {
    private Engine engine;
    public ComputerPlayer(ChessBoard board, Piece.PieceColor color, Engine engine) {
        this.pieceColor = color;
        this.chessBoard = board;
        this.engine = engine;
    }

    public final void makeAMove(Engine engine, MoveManager moveManager) {
        Move move = engine.getComputerPlayerMove(this);
        if(move != null) {
            move.execute();
            this.moveList.add(move);
            moveManager.storeMove(move);
            return;
        }
    }

}