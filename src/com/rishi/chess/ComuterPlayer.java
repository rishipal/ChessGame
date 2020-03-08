package com.rishi.chess;
import com.rishi.chess.engine.Engine;

class ComputerPlayer extends Player {
    private Engine engine;

    ComputerPlayer(ChessBoard board, Piece.PieceColor color) {
        this.pieceColor = color;
        this.engine = new Engine(board);
        this.chessBoard = board;
    }

    /** TODO: Use the engine to choose a move, and then make that move */
    public final void makeMove(MoveManager moveManager, Cell ignore, Cell ignore2) {
        Move move = engine.getComputerPlayerMove(this);
        Piece pieceMoved = move.source.piece;
        move.source.unsetPiece();
        move.destination.setPiece(pieceMoved);
        moveManager.storeMove(move);
        return;
    }
}