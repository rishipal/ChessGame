package com.rishi.chess;

class HumanPlayer extends Player {
    HumanPlayer(ChessBoard board, Piece.PieceColor color) {
        this.pieceColor = color;
        this.chessBoard = board;
    }

    /** Simply make the move from source to destination */
    public final void makeMove(MoveManager moveManager, Cell source, Cell destination) {
        Move m = new Move(source.piece, destination);
        Piece pieceMoved = source.piece;
        source.unsetPiece();
        destination.setPiece(pieceMoved);
        moveManager.storeMove(m);
    }
}