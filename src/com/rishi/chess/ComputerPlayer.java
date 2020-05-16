package com.rishi.chess;
import com.rishi.chess.engine.Engine;
import com.rishi.chess.utils.Utils;

public class ComputerPlayer extends Player {
    private Engine engine = null;
    public ComputerPlayer(ChessBoard board, Piece.PieceColor color, Engine engine) {
        this.pieceColor = color;
        this.chessBoard = board;
        this.engine = engine;
    }

    /** TODO: Use the engine to choose a move, and then make that move */
    public final void makeAMove(Engine engine, MoveManager moveManager, Cell ignore, Cell ignore2) {
        Move move = getComputerPlayerMove(this);
        if(move != null) {
            move.execute();
            moveManager.storeMove(move);
            return;
        }
    }


    private Move getComputerPlayerMove(Player computer) {
        engine.calculateMovesTree(computer);
        Utils.print("Calculated Computer moves tree");

        Move m = engine.pickNextMove();
        //m.printMove();
        return m;
    }

}